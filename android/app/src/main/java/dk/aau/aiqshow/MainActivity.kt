package dk.aau.aiqshow

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import dk.aau.iaqlibrary.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler.view.*
import org.json.JSONArray
import org.json.JSONObject
import dk.aau.iaqlibrary.BluetoothService.Companion as comp
import java.nio.charset.Charset
import java.time.LocalDateTime
import kotlin.math.round
import kotlin.math.roundToInt
import android.content.BroadcastReceiver as BroadcastReceiver1


private const val TAG = "MAIN_ACTIVITY_DEBUG"
const val PREFERENCES = "prefs"
private const val CHANNEL_ID = "dk.aau.aiqshow.alert"

fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBool() = this > 0

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

data class DataReading(val gasType: String, val value: Double, val time: LocalDateTime) {
    override fun toString(): String {
        return "Gas: $gasType, Value: ${value.roundToInt()}, Time: $time"
    }
}

data class Configuration(val mac: String, val subbed: Boolean, val guideline: String) {
    override fun toString(): String {
        return "CFG/[[\"$mac\",\"${subbed.toInt()}\",\"$guideline\"]]"
    }
}

data class Alert(val temperature: Double, val humidity: Double,
                 val co2: Double, val co: Double, val max : Pair<Double,String>,
                 val problem : String,val solution : List<String>) {
    override fun toString(): String {
        return ""
    }
}

class MainActivity : AppCompatActivity(), SuperFragment.InputListener {

    private var mmPrefs : SharedPreferences? = null
    private val mmBTAdapter : BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val mmWeakRef = WeakReference(this)
    private lateinit var mmHandler : MyHandler
    private val mmDeviceAddress : String = "B8:27:EB:4C:0D:D9"
    private val mmManager: FragmentManager = supportFragmentManager
    private lateinit var mmDevice : BluetoothDevice
    private lateinit var mmBTService : BluetoothService
    private lateinit var mmRecyclerView: RecyclerView
    private lateinit var mmNotificationManager: NotificationManager

    private class MyHandler(private val ref: WeakReference<MainActivity>) : Handler() {
        private var data: String = ""
        private var size: Int = 0
        private val prefs: SharedPreferences? = ref.get()!!.getSharedPreferences(PREFERENCES,0)
        override fun handleMessage(msg: Message) {
            val thing = (msg.obj as ByteArray).toString(Charset.defaultCharset()).take(msg.arg1)

            when (msg.what) {
                comp.MESSAGE_READ -> {Log.i("$TAG READ",thing); messageRead(msg)}

                comp.MESSAGE_CONT -> {Log.i("$TAG CONT", thing);data(thing)}

                comp.MESSAGE_WRITE -> Log.i("$TAG WRITE",thing)

                comp.MESSAGE_CONNECT -> { Log.i("$TAG CONNECTION",thing)
                    connect(msg.arg2)
                    ref.get()!!.mainText.text = thing
                }

                comp.MESSAGE_EMPTY -> Log.i("$TAG EMPTY", thing)

                comp.MESSAGE_ERROR -> {Log.i("$TAG ERROR", thing)
                    if (msg.arg2 == comp.ERROR_CONNECT)
                        ref.get()!!.mainText.text = "Connection Error"
                }

                else -> Log.i(TAG, thing)
            }
        }

        private fun messageRead(msg : Message) {
            val thing = (msg.obj as ByteArray).toString(Charset.defaultCharset()).take(msg.arg1).drop(4)

            when (msg.arg2) {
                comp.CONTENT_ACKNOWLEDGE -> ref.get()!!.mmBTService.get(comp.getConfig())
                comp.CONTENT_ALERT -> alert(alertJSON(JSONObject(thing)))
                comp.CONTENT_CONFIG -> config(configJSON(JSONArray(thing)))
                comp.CONTENT_DATA -> data(thing)
                else -> throw Exception()
            }
        }

        private fun alert(thing: Alert) {
            val str = "A problem: ${thing.problem} has been detected, it " +
                    "is recommended to either ${thing.solution[0]} or ${thing.solution[1]}"
            ref.get()!!.sendNotification(thing.problem,str)
            Log.i(TAG,str)
        }

        private fun config(thing: Configuration) {
            if (thing.subbed) { prefs!!.edit().putBoolean("alert",true).apply() }
            else { prefs!!.edit().putBoolean("alert",false).apply() }
        }

        private fun data(thing: String) {
            val len = thing.length
            data += thing

            if (len > size)
                size = len

            if (thing[len-1] == ']' && thing[len-2] == ']') {
                ref.get()!!.mainText.text = "Data Received"
                Log.i("$TAG SIZE",size.toString() + " " + data.length.toString())
                val list = dataJSON(JSONArray(data))

                ref.get()!!.mmRecyclerView.adapter = DataAdapter(list.toTypedArray())
                ref.get()!!.mmRecyclerView.adapter?.notifyDataSetChanged()
                data = ""
            }
        }

        private fun connect(type: Int) {
            if (type == 1) { // connected
                ref.get()!!.buttonDisconnect.isEnabled = true
                ref.get()!!.buttonWrite.isEnabled = true
                ref.get()!!.buttonConnect.isEnabled = false
            } else {
                ref.get()!!.buttonDisconnect.isEnabled = false
                ref.get()!!.buttonWrite.isEnabled = false
                ref.get()!!.buttonConnect.isEnabled = true
            }
        }

        private fun dataJSON(array: JSONArray) : List<DataReading> {
            val list: MutableList<DataReading> = mutableListOf()
            var i = 0
            while (!array.isNull(i)) {
                val type = array.getJSONArray(i).getString(0)
                val value = array.getJSONArray(i).getDouble(1)
                val time = array.getJSONArray(i).getString(2)
                list.add(DataReading(type,value, LocalDateTime.parse("${time.substring(0..9)}T${time.substring(11)}")))
                i++
            }
            return list.toList()
        }

        private fun configJSON(array:JSONArray) : Configuration {

            val mac = array.getJSONArray(0).getString(0)
            val sub = array.getJSONArray(0).getInt(1).toBool()
            val guideline = array.getJSONArray(0).getString(2)

            return Configuration(mac,sub,guideline)
        }

        private fun alertJSON(json : JSONObject) : Alert {

            val temp = json.getDouble("temperature").round(2)
            val hum = json.getDouble("humidity").round(2)
            val co2 = json.getDouble("co2").round(2)
            val co = json.getDouble("co").round(2)
            val max1 = json.getJSONArray("max")
            val max : Pair<Double,String> = Pair(max1.getDouble(0).round(2),max1.getString(1))
            val problem = json.getString("problem")
            val sol = json.getJSONArray("solution")
            val solution : MutableList<String> = mutableListOf()

            var i = 0
            while (!sol.isNull(i)) {
                solution.add(sol.getString(i))
                i++
            }
            return Alert(temp,hum,co2,co,max,problem,solution.toList())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mmPrefs = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        mmHandler = MyHandler(mmWeakRef)

        mmNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (!mmBTAdapter.isEnabled){
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
        }

        mmDevice = mmBTAdapter.getRemoteDevice(mmDeviceAddress)
        val pairedDevices: Set<BluetoothDevice>? = mmBTAdapter.bondedDevices
        val device = pairedDevices?.find { it.name == "Beacon 1" } ?: mmDevice

        if (pairedDevices != null) {
            for((i, x) in pairedDevices.withIndex()) {
                Log.i(TAG, "${x.name} $i")
            }
        }

        createNotificationChannel(
            CHANNEL_ID,
            "IAQSHOW ALERT",
            "lul")

        if (!mmPrefs!!.getBoolean("alert",false)) {
            mmPrefs!!.edit().putBoolean("alert",false).apply()
        }

        mmRecyclerView = findViewById<RecyclerView>(R.id.Recycler).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DataAdapter(arrayOf())
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

    override fun onRestart() {
        super.onRestart()

        if (!mmBTService.connected)
            mmBTService.connect()
    }

    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.lockscreenVisibility = 1
        channel.canBypassDnd()
        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.shouldVibrate()
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        mmNotificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification(content: String, text: String) {
        val notificationID = 101
        val notification = Notification.Builder(this@MainActivity,
            CHANNEL_ID)
            .setContentTitle("Alert")
            .setContentText(content)
            .setStyle(Notification.BigTextStyle().bigText(text))
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setChannelId(CHANNEL_ID)
            .build()

        mmNotificationManager.notify(notificationID, notification)

    }

    override fun onDestroy() {
        super.onDestroy()
        mmBTService.disconnect()
    }

    override fun onGET(text: String) {
        mainText.text = text
        mmBTService.get(text)
    }

    override fun onSET(text: String) {
        mainText.text = text
        mmBTService.set(text)
    }

}

class DataAdapter(private val dataSet: Array<DataReading>) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.recycler, p0, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.view.textView.text = dataSet[p1].toString()
    }
}