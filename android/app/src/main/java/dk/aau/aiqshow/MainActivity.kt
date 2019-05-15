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


private const val TAG = "MAIN_ACTIVITY_DEBUG"

class MainActivity : AppCompatActivity(), SuperFragment.InputListener {

    private val _btAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val _weakRef = WeakReference(this)
    private val _handler = MyHandler(_weakRef)
    private val _deviceAddress : String = "B8:27:EB:4C:0D:D9"
    private val _manager: FragmentManager = supportFragmentManager
    private lateinit var _device : BluetoothDevice
    private lateinit var _btService : MyBluetoothService


    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val thing = if (msg.what != MESSAGE_TOAST)
                msg.obj as String
            else msg.data.getString("toast")

            ref.get()!!.mainText.text = thing
            when {
                msg.what == MESSAGE_ERROR -> Log.i("$TAG READ",thing)
                msg.what == MESSAGE_WRITE -> Log.i("$TAG WRITE",thing)
                msg.what == MESSAGE_TOAST -> Log.i("$TAG TOAST",thing)
                msg.what == MESSAGE_CONNECT -> Log.i("$TAG CONNECTED",thing)
                msg.what == MESSAGE_EMPTY -> Log.i("$TAG EMPTY", thing)
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



        buttonConnect.setOnClickListener {
            _btService = MyBluetoothService(_handler,device = device)
            _btService.connect()
        }

        buttonDisconnect.setOnClickListener {
            _btService.disconnect()
        }

        buttonWrite.setOnClickListener {
            val dialog = InputDialog()
            dialog.show(_manager, "InputDialog")
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

}