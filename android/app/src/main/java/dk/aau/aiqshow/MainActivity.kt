package dk.aau.aiqshow

import android.app.Dialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import dk.aau.iaqlibrary.MyBluetoothService
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.time.LocalDateTime
import java.util.*
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import kotlinx.android.synthetic.main.write_fragment.*


private const val TAG = "MAIN_ACTIVITY_DEBUG"

class MainActivity : AppCompatActivity() {

    //TODO: ask to turn on bluetooth
    private val _btAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val _weakRef = WeakReference(this)
    private val _handler = MyHandler(_weakRef)
    private val _device : BluetoothDevice = _btAdapter.getRemoteDevice("B8:27:EB:4C:0D:D9")
    private var _btService : MyBluetoothService = MyBluetoothService(
        _handler,
        UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee"),
        _device)
    var isFragmentLoaded = false
    val manager = supportFragmentManager

    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val thing = if (msg.what != 2)
                msg.obj as String
            else msg.data.getString("toast")

            ref.get()!!.mainText.text = thing
            when {
                msg.what == 0 -> Log.i("$TAG READ",thing)
                msg.what == 1 -> Log.i("$TAG WRITE",thing)
                msg.what == 2 -> Log.i("$TAG TOAST",thing)
                msg.what == 3 -> Log.i("$TAG CONNECTED",thing)
                msg.what == 4 -> Log.i("$TAG MESSAGE EMPTY", thing)
                else -> Log.i(TAG, "ERROR")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonConnect.setOnClickListener {
            val pairedDevices: Set<BluetoothDevice>? = _btAdapter.bondedDevices
            val device = pairedDevices?.find { it.name.contains("CreamPi") } ?: _device
            _btService = MyBluetoothService(_handler,device = device)

            _btService.connect()
        }

        buttonDisconnect.setOnClickListener {
            _btService.disconnect()
        }

        buttonWrite.setOnClickListener {
            /*val timeInterval = _btService.getTimeInterval("CO", LocalDateTime.now().minusMinutes(5), LocalDateTime.now())
            val value = _btService.getValue("CO", value = 50f)
            _btService.get(timeInterval, value)*/
            //if(!isFragmentLoaded)
            //    ShowFragment()
            val testDialog = TestDialog()
            testDialog.show(manager, "test")
        }
    }

    override fun onButtonClick(text: String) {
        mainText.text = text

    }

    override fun onDestroy() {
        super.onDestroy()
        _btService.disconnect()
    }

    private fun discovery() : BluetoothDevice {
        throw NotImplementedError("lul")
    }

    fun ShowFragment(){
        val transaction = manager.beginTransaction()
        val fragment = WriteFragment()
        transaction.add(R.id.writeFragment, fragment)
        transaction.commit()
        Log.d("Fragment", "ShowFragment()")
        isFragmentLoaded = true
    }

    fun HideFragment(){
        val transaction = manager.beginTransaction()
        val fragment = manager.findFragmentById(R.id.writeFragment)
        transaction.remove(fragment!!)
        transaction.commit()
        Log.d("Fragment", "HideFragment()")
        isFragmentLoaded = false
    }

}