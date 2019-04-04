package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.util.*


private const val TAG = "MAIN_ACTIVITY_DEBUG"
var preferences : SharedPreferences? = null

class MainActivity : AppCompatActivity() {

    private val _btAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val _weakRef = WeakReference(this)
    private val _handler = MyHandler(_weakRef)
    private val _device : BluetoothDevice = _btAdapter.getRemoteDevice("B8:27:EB:4C:0D:D9")
    private val _bTService : MyBluetoothService = MyBluetoothService(_handler, _device)



    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
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
            _bTService.connect()
        }

        buttonDisconnect.setOnClickListener {
            _bTService.disconnect()
        }

        buttonWrite.setOnClickListener {
            _bTService.getCO()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bTService.disconnect()
    }

    private fun setPrefs() {
        if (preferences?.getString("UUID","error") == "error") {
            val uuid = UUID.randomUUID()
            preferences!!.edit().putString("UUID",uuid.toString()).apply()
        }
    }
}
