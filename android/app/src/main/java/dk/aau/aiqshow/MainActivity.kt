package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*


private val TAG = "MAIN_ACTIVITY"
var preferences : SharedPreferences? = null

class MainActivity : AppCompatActivity() {

    lateinit var btadapter : BluetoothAdapter
    lateinit var BTService : MyBluetoothService
    lateinit var device : BluetoothDevice

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            Toast.makeText(this@MainActivity,ByteArrayToString(msg.obj as ByteArray, msg.arg1),Toast.LENGTH_SHORT).show()
            text.text = ByteArrayToString(msg.obj as ByteArray, msg.arg1)
            if (msg.arg1 > 0)
                Log.i(TAG,ByteArrayToString(msg.obj as ByteArray, msg.arg1))
            else
                Log.i(TAG,"SENT MESSAGE")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = this.getSharedPreferences("prefs",0)

        setPrefs()
        btadapter = BluetoothAdapter.getDefaultAdapter()
        BTService = MyBluetoothService(handler)
        device = btadapter.getRemoteDevice("B8:27:EB:4C:0D:D9")

        Log.d(TAG,preferences!!.getString("UUID","error"))


    }

    fun buttonConnect(view: View) {
        try {
            BTService.ConnectThread(device).start()
            Thread.sleep(500)
            BTService.ConnectedThread().start()
        }
        catch (e : Exception) {
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            Log.e(TAG,e.message)
        }
    }

    fun buttonDisConnect(view: View) {
        try {
            BTService.ConnectThread(device).cancel()
        }
        catch (e : Exception) {
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            Log.e(TAG,e.message)
        }
    }

    fun buttonWrite(view: View) {
        try {
            BTService.ConnectedThread().write(("GET").toByteArray())
        }
        catch (e : Exception) {
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            Log.e(TAG,e.message)
        }
    }

    private fun setPrefs() {
        if (preferences?.getString("UUID","error") == "error") {
            val uuid = UUID.randomUUID()
            preferences!!.edit().putString("UUID",uuid.toString()).apply()
        }
    }

    private fun ByteArrayToString(ba : ByteArray, size: Int) : String {
        return ba.slice(0 until size).toByteArray().toString(Charsets.UTF_8)
    }
}
