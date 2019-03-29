package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.ref.WeakReference
import java.util.*


private const val TAG = "MAIN_ACTIVITY_DEBUG"
var preferences : SharedPreferences? = null

class MainActivity : AppCompatActivity() {

    private val _btAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val _weakRef = WeakReference(this)
    private val _handler = myHandler(_weakRef)
    private val _bTService : MyBluetoothService = MyBluetoothService(_handler)
    private val _device : BluetoothDevice = _btAdapter.getRemoteDevice("B8:27:EB:4C:0D:D9")
    private lateinit var _connect : MyBluetoothService.ConnectThread
    private lateinit var _connected : MyBluetoothService.ConnectedThread


    class myHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val array = msg.obj as ByteArray
            val size = msg.arg1

            ref.get()!!.text.text = ByteArrayToString(array, size)
            when {
                msg.what == 0 -> Log.i("$TAG READ",ByteArrayToString(array, size))
                msg.what == 1 -> Log.i("$TAG WRITE",ByteArrayToString(array, size))
                msg.what == 2 -> Log.i("$TAG TOAST",ByteArrayToString(array, size))
                else -> Log.i(TAG, "ERROR")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = this.getSharedPreferences("prefs",0)

        setPrefs()
        Log.d(TAG,preferences!!.getString("UUID","error"))

        buttonConnect.setOnClickListener {
            try {
                _connect = _bTService.ConnectThread(_device)
                _connect.start()
                Thread.sleep(500)
                _connected = _bTService.ConnectedThread()
                _connected.start()
            }
            catch (e : Exception) {
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
                Log.e(TAG,e.message)
            }
        }

        buttonDisconnect.setOnClickListener {
            try {
                _bTService.ConnectThread(_device).cancel()
            }
            catch (e : Exception) {
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
                Log.e(TAG,e.message)
            }
        }

        buttonWrite.setOnClickListener {
            try {
                _bTService.ConnectedThread().write(("GET").toByteArray())
            }
            catch (e : Exception) {
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
                Log.e(TAG,e.message)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _connected.cancel()
        _connect.cancel()
    }

    private fun setPrefs() {
        if (preferences?.getString("UUID","error") == "error") {
            val uuid = UUID.randomUUID()
            preferences!!.edit().putString("UUID",uuid.toString()).apply()
        }
    }
}
