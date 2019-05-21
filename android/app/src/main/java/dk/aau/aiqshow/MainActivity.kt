package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import dk.aau.iaqlibrary.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import android.support.v4.app.FragmentManager
import dk.aau.iaqlibrary.BluetoothService.Companion as comp
import java.nio.charset.Charset


private const val TAG = "MAIN_ACTIVITY_DEBUG"

class MainActivity : AppCompatActivity(), SuperFragment.InputListener {

    private val mmBTAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val mmWeakRef = WeakReference(this)
    private val mmHandler = MyHandler(mmWeakRef)
    private val mmDeviceAddress : String = "B8:27:EB:4C:0D:D9"
    private val mmManager: FragmentManager = supportFragmentManager
    private lateinit var mmDevice : BluetoothDevice
    private lateinit var mmBTService : BluetoothService


    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val thing1 = msg.obj as ByteArray
            val thing = thing1.toString(Charset.defaultCharset()).take(msg.arg1)

            ref.get()!!.mainText.text = thing
            when {
                msg.what == comp.MESSAGE_READ -> Log.i("$TAG READ",thing)
                msg.what == comp.MESSAGE_WRITE -> Log.i("$TAG WRITE",thing)
                msg.what == comp.MESSAGE_CONNECT -> Log.i("$TAG CONNECTED",thing)
                msg.what == comp.MESSAGE_EMPTY -> Log.i("$TAG EMPTY", thing)
                else -> Log.i(TAG, "ERROR")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!mmBTAdapter.isEnabled){
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
        }

        mmDevice = mmBTAdapter.getRemoteDevice(mmDeviceAddress)
        val pairedDevices: Set<BluetoothDevice>? = mmBTAdapter.bondedDevices
        val device = pairedDevices?.find { it.name.contains("Beacon 1") } ?: mmDevice

        if (pairedDevices != null) {
            for((i, x) in pairedDevices.withIndex()) {
                Log.i(TAG, "${x.name} $i")
            }
        }

        buttonConnect.setOnClickListener {
            mmBTService = BluetoothService(mmHandler, device)
            mmBTService.connect()
        }

        buttonDisconnect.setOnClickListener {
            mmBTService.disconnect()

        }

        buttonWrite.setOnClickListener {
            val dialog = InputDialog()
            dialog.show(mmManager, "InputDialog")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mmBTService.disconnect()
    }

    override fun onSend(text: String) {
        mainText.text = text
        mmBTService.get(text)
    }

}