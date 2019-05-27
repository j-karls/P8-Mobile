package dk.aau.iaqlibrary

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


fun Boolean.toInt() = if (this) 1 else 0

private const val TAG = "BLUETOOTH_SERVICE_DEBUG"
private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH.mm")
/**Is a module for working with the sensor-box
 * @param handler is used to handle messages sent from this module as well as messages sent from the connected Bluetooth device
 * @param device is the connected device*/
class BluetoothService(private val handler: Handler, private val device: BluetoothDevice) {
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    lateinit var mmSocket : BluetoothSocket
    private val mmCommThread = CommunicationThread()
    private val mmConnThread = ConnectThread()
    var connected : Boolean = false
        private set

    private fun createBluetoothSocket(device: BluetoothDevice) : BluetoothSocket {
        try {
            val method = device.javaClass.getMethod("createInsecureRfcommSocketToServiceRecord", UUID::class.java)
            return  method.invoke(device, uuid) as BluetoothSocket
        } catch (e: Exception) {
            Log.e(TAG, "Could not create Insecure RFComm Connection", e)
        }
        return  device.createRfcommSocketToServiceRecord(uuid)
    }

    private inner class CommunicationThread : Thread() {
        // Create stream sockets
        private lateinit var mmInStream: InputStream
        private lateinit var mmOutStream: OutputStream
        private var running = true

        override fun interrupt() {
            running = false
        }

        override fun run() {
            try {
                mmInStream = mmSocket.inputStream
                mmOutStream = mmSocket.outputStream
            } catch (e: IOException) {
                Log.e(TAG,e.toString() + "Could not open Input/Output Sockets")
            }

            var buffer: ByteArray  // buffer store for the stream
            var bytes: Int // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (running) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.available()
                    if (bytes != 0) {
                        Log.i(TAG,"Message available, payload: $bytes")
                        /* large packages received are between ~12-16k bytes, so an extra 4k is added to the buffer to
                        ensure adequate size*/
                        buffer = ByteArray(20000)
                        SystemClock.sleep(100) //pause and wait for rest of data.
                        bytes = mmInStream.available() // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes) // record how many bytes we actually read

                        // check the received operation code
                        val operationCode = buffer.sliceArray(0..2).toString(Charset.defaultCharset())

                        // if the request is "empty" two braces "[]" will be received
                        if (bytes > 6)
                            when (operationCode) {
                                "DAT" -> handler.obtainMessage(MESSAGE_READ, bytes, CONTENT_DATA, buffer).sendToTarget()
                                "ACK" -> handler.obtainMessage(MESSAGE_READ, bytes, CONTENT_ACKNOWLEDGE, buffer).sendToTarget()
                                "CFG" -> handler.obtainMessage(MESSAGE_READ, bytes, CONTENT_CONFIG, buffer).sendToTarget()
                                "ALT" -> handler.obtainMessage(MESSAGE_READ, bytes, CONTENT_ALERT, buffer).sendToTarget()
                                // if no operationCode is received, then the data is a continuation of Data
                                else -> handler.obtainMessage(MESSAGE_CONT, bytes, CONTENT_DATA, buffer).sendToTarget()
                            }
                        else
                            handler.obtainMessage(MESSAGE_EMPTY, bytes, -1, buffer).sendToTarget()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    connected = false
                    handler.obtainMessage(MESSAGE_ERROR, 10, ERROR_READ, "Read Error".toByteArray()).sendToTarget()
                    break
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(input: String) {
            val bytes = input.toByteArray() //converts entered String into bytes
            try {
                mmOutStream.write(bytes)
                // also sends the written message to the handler for confirmation that is has been sent
                handler.obtainMessage(MESSAGE_WRITE, bytes.size, -1, bytes).sendToTarget()
            } catch (e: IOException) {
                // if message could not be sent, the bluetooth connection must have been halted
                connected = false
            }

        }

        // Call this from the main activity to shutdown the connection
        fun cancel() {
            try {
                this.interrupt()
                // must wait for the current loop to finish before closing the socket, else a read-error will arise
                sleep(100)
                mmSocket.close()
                handler.obtainMessage(MESSAGE_CONNECT, 12, 0, "Disconnected".toByteArray()).sendToTarget()
                connected = false
            } catch (e: IOException) {
                Log.e(TAG,e.toString())
            }

        }
    }

    private inner class ConnectThread : Thread() {

        override fun run() {
            var fail = false

            // opens a socket to the given device
            try {
                mmSocket = createBluetoothSocket(device)
            } catch (e: IOException) {
                fail = true
            }

            // Establish the Bluetooth socket connection
            try {
                mmSocket.connect()
            } catch (e: IOException) {
                try {
                    fail = true
                    mmSocket.close()
                    handler.obtainMessage(MESSAGE_ERROR, e.toString().length, ERROR_CONNECT, e.toString().toByteArray())
                        .sendToTarget()
                } catch (e2: IOException) {
                    Log.e(TAG, "SUPER ERROR $e2")
                }

            }

            // if the connection did not fail, start the communication thread, and inform the handler.
            if (!fail) {
                mmCommThread.start()

                connected = true

                handler.obtainMessage(MESSAGE_CONNECT, 9, 1, "Connected".toByteArray())
                    .sendToTarget()
            }
        }

        // cancels the entire Bluetooth connection, also communication
        fun cancel() {
            try {
                this.interrupt()
                mmCommThread.cancel()
            } catch (e: Exception) {
                Log.e(TAG,e.toString())
            }

        }
    }

    // attempts to open Bluetooth communication
    fun connect() {
        mmConnThread.start()
    }

    // closes Bluetooth connection
    fun disconnect() {
        mmConnThread.cancel()
    }

    // function used to write to the connected Bluetooth device
    private fun write(str: String) {
        try { mmCommThread.write(str) }
        catch (e : Exception) { Log.e(TAG,e.message) }
    }

    /** Collects and sends a number of messages to the connected Bluetooth device
     * @param args takes a variable number of strings and formats them to work with the sensor-box API
     * @throws IllegalArgumentException when no strings are applied
     * */
    fun get(vararg args: String) {
        if (args.isNotEmpty()) {
            val str = args.foldRight("") {currentValue, result -> "$currentValue & $result" }.dropLast(3)
            write("GET $str")
        }
        else throw IllegalArgumentException("args cannot be empty")
    }

    // string builder for set requests, might not be useful for anything
    fun set(vararg args: String) {
        if (args.isNotEmpty()) {
            val str = args.foldRight("") {currentValue, result -> "$currentValue & $result" }.dropLast(3)
            write("SET $str")
        }
        else throw IllegalArgumentException("args cannot be empty")
    }

    /**Writes unformatted to the device, USE AT OWN RISK*/
    fun rawWrite(arg: String) {
        write(arg)
    }

    // companion that contains string builders for different commands one can use to communication with the Bluetooth
    // device, also contains message, error and content codes to be used by the handler.
    companion object {
        // operation codes that differentiate between the different types of messages that is sent to the handler
        const val MESSAGE_READ: Int = 0
        const val MESSAGE_WRITE: Int = 1
        const val MESSAGE_CONNECT: Int = 2
        const val MESSAGE_EMPTY: Int = 3
        const val MESSAGE_ERROR: Int = 4
        const val MESSAGE_CONT: Int = 5

        // error codes
        const val ERROR_CONNECT: Int = 0
        const val ERROR_READ: Int = 1

        // codes for differentiating between read messages
        const val CONTENT_ACKNOWLEDGE: Int = 0  //ACK
        const val CONTENT_DATA: Int = 1         //DAT
        const val CONTENT_CONFIG: Int = 2       //CFG
        const val CONTENT_ALERT: Int = 3        //ALT


        fun getTimeInterval(gasType: String, from: LocalDateTime, to: LocalDateTime) : String {
            val fromDate = from.format(formatter)
            val toDate = to.format(formatter)
            return ("$gasType time $fromDate to $toDate")
        }

        fun getTime(gasType: String, compare: String, time: LocalDateTime = LocalDateTime.now()) : String {
            val timeDate = time.format(formatter)
            return ("$gasType time $compare $timeDate")
        }

        fun getValue(gasType: String, compare: String = ">", value: Float = 0f) : String {
            return ("$gasType value $compare $value")
        }

        fun subAlerts(): String {
            return ("alerts = true")
        }

        fun unSubAlerts(): String {
            return ("alerts = false")
        }

        fun getStatus(gasType: String): String {
            return ("$gasType status")
        }

        fun getConfig(): String {
            return ("config")
        }

        fun setGuidelines(guideline: String = "WHO") : String {
            return ("guideline $guideline")
        }

        /** Formats a LocalDateTime to
         * @param time the LocalDateTime to be formatted
         * @see dateTimeFormatter
         * @return a dateTime formatted string that works with the Sensor-box API*/
        fun dateTimeFormatter(time: LocalDateTime) : String {
            return time.format(formatter)
        }
    }
}

// a data class used to contain data readings from the sensor-box
data class DataReading(val gasType: String, val value: Double, val time: LocalDateTime) {
    override fun toString(): String {
        return "Gas: $gasType, Value: ${value.roundToInt()}, Time: $time"
    }
}
// a data class for containing a configuration
data class Configuration(val mac: String, val subbed: Boolean, val guideline: String) {
    override fun toString(): String {
        return "CFG/[[\"$mac\",\"${subbed.toInt()}\",\"$guideline\"]]"
    }
}
// a data class for containing an alert received from the sensor-box
data class Alert(val temperature: Double, val humidity: Double,
                 val co2: Double, val co: Double, val max : Pair<Double,String>,
                 val problem : String,val solution : List<String>) {
    override fun toString(): String {
        return "ALT/${toJson()}"
    }

    private fun toJson() : JSONObject {
        val thing = JSONObject()

        thing.put("temperature",temperature)
        thing.put("humidity",humidity)
        thing.put("co2",co2)
        thing.put("co",co)
        thing.put("max",max)
        thing.put("problem",problem)
        thing.put("solution",solution)

        return thing
    }
}