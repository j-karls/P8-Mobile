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
import org.json.JSONArray
import dk.aau.iaqlibrary.BluetoothService.Companion as comp
import java.nio.charset.Charset
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt


private const val TAG = "MAIN_ACTIVITY_DEBUG"

class MainActivity : AppCompatActivity(), SuperFragment.InputListener {

    private val mmBTAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val mmWeakRef = WeakReference(this)
    private val mmHandler = MyHandler(mmWeakRef)
    private val mmDeviceAddress : String = "B8:27:EB:4C:0D:D9"
    private val mmManager: FragmentManager = supportFragmentManager
    private lateinit var mmDevice : BluetoothDevice
    private lateinit var mmBTService : BluetoothService


    data class dataReading(val gasType: String, val value: Float, val time: LocalDateTime)


    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        private var data: String = ""
        private var size: Int = 0
        override fun handleMessage(msg: Message) {
            val thing1 = msg.obj as ByteArray
            val thing = thing1.toString(Charset.defaultCharset()).take(msg.arg1)

            when {
                msg.what == comp.MESSAGE_READ -> {Log.i("$TAG READ",thing); read(thing)}
                msg.what == comp.MESSAGE_WRITE -> Log.i("$TAG WRITE",thing)
                msg.what == comp.MESSAGE_CONNECT -> {Log.i("$TAG CONNECTION",thing); connect(msg.arg2)
                    ref.get()!!.mainText.text = thing}
                msg.what == comp.MESSAGE_EMPTY -> Log.i("$TAG EMPTY", thing)
                msg.what == comp.MESSAGE_ERROR -> {Log.i("$TAG ERROR", thing);
                    if (msg.arg2 == comp.ERROR_CONNECT)
                        ref.get()!!.mainText.text = "Connection Error"
                }
                else -> Log.i(TAG, thing)
            }
        }

        private fun read(thing: String) {
            val len = thing.length
            data += thing
            if (len > size)
                size = len
            if (thing[len-1] == ']' && thing[len-2] == ']') {
                ref.get()!!.mainText.text = "Data Received"
                //Log.i(TAG,data)
                Log.i("$TAG SIZE",size.toString() + " " + data.length.toString())
                val json = jsonStuff(JSONArray(data))
                Log.i("$TAG JSON",json[0].gasType)
                data = ""
            }
        }

        fun jsonStuff(array: JSONArray) : List<dataReading> {
            val list: MutableList<dataReading> = mutableListOf()
            var i = 0
            while (!array.isNull(i)) {
                val type = array.getJSONArray(i).getString(0)
                val value = array.getJSONArray(i).getDouble(1)
                val time = array.getJSONArray(i).getString(2)
                list.add(dataReading(type,value.toFloat(), LocalDateTime.parse("${time.substring(0..9)}T${time.substring(11)}")))
                i++
            }
            return list.toList()
        }


        private fun connect(type: Int) {
            if (type == 1) {
                ref.get()!!.buttonDisconnect.isEnabled = true
                ref.get()!!.buttonWrite.isEnabled = true
                ref.get()!!.buttonConnect.isEnabled = false
            } else {
                ref.get()!!.buttonDisconnect.isEnabled = false
                ref.get()!!.buttonWrite.isEnabled = false
                ref.get()!!.buttonConnect.isEnabled = true
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
        val rndm = Random(45354345)
        stuffButton.setOnClickListener {
            mmBTService.get(comp.getValue("CO2",">", rndm.nextInt((0..7000)).toFloat()))
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