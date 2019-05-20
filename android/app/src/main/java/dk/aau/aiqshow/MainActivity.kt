package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import dk.aau.iaqlibrary.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import android.support.v4.app.FragmentManager
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.util.*
import dk.aau.iaqlibrary.MyBluetoothService.Companion as comp
import android.widget.Toast



private const val TAG = "MAIN_ACTIVITY_DEBUG"

class MainActivity : AppCompatActivity(), SuperFragment.InputListener {

    private val _btAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val _weakRef = WeakReference(this)
    private val _handler = MyHandler(_weakRef)
    private val _deviceAddress : String = "B8:27:EB:4C:0D:D9"
    private val _manager: FragmentManager = supportFragmentManager
    private lateinit var _device : BluetoothDevice
    private lateinit var _btService : MyBluetoothService
    private lateinit var _connect : ConnectedThread
    private lateinit var _succ : BluetoothSocket


    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val thing1 = msg.obj as ByteArray
            val thing = thing1.contentToString()

            Log.i(TAG,msg.what.toString() + " " + msg.arg1.toString() + " " + msg.arg2.toString() + " " + thing)

            ref.get()!!.mainText.text = thing
            when {
                msg.what == comp.MESSAGE_READ -> Log.i("$TAG READ",thing)
                msg.what == comp.MESSAGE_WRITE -> Log.i("$TAG WRITE",thing)
                msg.what == comp.MESSAGE_TOAST -> Log.i("$TAG TOAST",thing)
                msg.what == comp.MESSAGE_CONNECT -> Log.i("$TAG CONNECTED",thing)
                msg.what == comp.MESSAGE_EMPTY -> Log.i("$TAG EMPTY", thing)
                else -> Log.i(TAG, "ERROR")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!_btAdapter.isEnabled){
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
        }

        _device = _btAdapter.getRemoteDevice(_deviceAddress)
        val pairedDevices: Set<BluetoothDevice>? = _btAdapter.bondedDevices
        val device = pairedDevices?.find { it.name.contains("Beacon 1") } ?: _device

        if (pairedDevices != null) {
            for((i, x) in pairedDevices.withIndex()) {
                Log.i(TAG, "${x.name} $i")
            }
        }

        buttonConnect.setOnClickListener {
            /*_btService = MyBluetoothService(_handler,device = device)
            _btService.connect()*/
            object : Thread() {
                override fun run() {
                    var fail = false

                    try {
                        _succ = createBluetoothSocket(device)
                    } catch (e: IOException) {
                        fail = true
                        Toast.makeText(baseContext, "Socket creation failed", Toast.LENGTH_SHORT).show()
                    }

                    // Establish the Bluetooth socket connection.
                    try {
                        _succ.connect()
                    } catch (e: IOException) {
                        try {
                            fail = true
                            _succ.close()
                            _handler.obtainMessage(1, -1, -1, byteArrayOf(0))
                                .sendToTarget()
                        } catch (e2: IOException) {
                            //insert code to deal with this
                            Toast.makeText(this@MainActivity, "Socket creation failed", Toast.LENGTH_SHORT).show()
                        }

                    }

                    if (!fail) {
                        _connect = ConnectedThread(_succ)
                        _connect.start()

                        _handler.obtainMessage(1, 1, -1, byteArrayOf(1))
                            .sendToTarget()
                    }
                }
            }.start()
        }

        buttonDisconnect.setOnClickListener {
            //_btService.disconnect()
            _connect.cancel()

        }

        buttonWrite.setOnClickListener {
            /*val dialog = InputDialog()
            dialog.show(_manager, "InputDialog")*/
            _connect.write("1")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _btService.disconnect()
    }

    override fun onSend(text: String) {
        mainText.text = text
        _btService.get(text)
    }

    val BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private fun createBluetoothSocket(device: BluetoothDevice): BluetoothSocket {
        try {
            val m = device.javaClass.getMethod("createInsecureRfcommSocketToServiceRecord", UUID::class.java)
            return m.invoke(device, BTMODULEUUID) as BluetoothSocket
        } catch (e: Exception) {
            Log.e(TAG, "Could not create Insecure RFComm Connection", e)
        }

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID)
    }

    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = mmSocket.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
            }

            mmInStream = tmpIn
            mmOutStream = tmpOut
        }

        override fun run() {
            var buffer: ByteArray  // buffer store for the stream
            var bytes: Int // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream!!.available()
                    if (bytes != 0) {
                        buffer = ByteArray(1024)
                        SystemClock.sleep(100) //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available() // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes) // record how many bytes we actually read
                        _handler.obtainMessage(MyBluetoothService.MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget() // Send the obtained bytes to the UI activity
                    }
                } catch (e: IOException) {
                    e.printStackTrace()

                    break
                }

            }
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(input: String) {
            val bytes = input.toByteArray()           //converts entered String into bytes
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
            }

        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
            }

        }
    }

}