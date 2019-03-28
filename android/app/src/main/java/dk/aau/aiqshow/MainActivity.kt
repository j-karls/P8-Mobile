package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.Exception
import java.util.*


private val TAG = "MAIN_ACTIVITY"
var preferences : SharedPreferences? = null

class MainActivity : AppCompatActivity() {

    public lateinit var btadapter : BluetoothAdapter

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            Toast.makeText(this@MainActivity,msg.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = this.getSharedPreferences("prefs",0)

        setPrefs()

        Log.d(TAG,preferences!!.getString("UUID","error"))
        btadapter = BluetoothAdapter.getDefaultAdapter()

    }

    fun buttonClick(view: View) {
        Log.d(TAG,"START-BT")
        val device: BluetoothDevice = btadapter.getRemoteDevice("B8:27:EB:4C:0D:D9")
        try {
            val a = MyBluetoothService(handler).ConnectThread(device)
        }
        catch (e : Exception) {
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }
        Log.d(TAG,"END-BT")
    }

    private fun setPrefs() {
        if (preferences?.getString("UUID","error") == "error") {
            val uuid = UUID.randomUUID()
            preferences!!.edit().putString("UUID",uuid.toString()).apply()
        }
    }
}
