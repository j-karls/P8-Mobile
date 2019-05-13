package dk.aau.iaqlibrary

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


private const val TAG = "BLUETOOTH_SERVICE_DEBUG"
private val formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy:hh.mm.ss")

// Defines several constants used when transmitting messages between the
// service and the UI.
const val MESSAGE_READ: Int = 0
const val MESSAGE_WRITE: Int = 1
const val MESSAGE_TOAST: Int = 2
const val MESSAGE_CONNECT: Int = 3
const val MESSAGE_EMPTY: Int = 4
const val MESSAGE_ERROR: Int = 5
// ... (Add other message types here as needed.)

class MyBluetoothService(
    // handler that gets info from Bluetooth service
    private val handler: Handler,
    // optional UUID
    uuid : UUID? = null,
    device: BluetoothDevice) {
    private val mmSocket: BluetoothSocket =
        try {Log.i(TAG,device.name)
            device.createRfcommSocketToServiceRecord(uuid)}
        catch (e: Exception) { Log.e(TAG,e.toString())
            try {device.javaClass.getMethod("createRfcommSocket", (Int::class.javaPrimitiveType))
                .invoke(device,1) as BluetoothSocket }
            catch (e: Exception) {Log.e(TAG, e.toString())
                throw Exception("No bluetooth connection could be created")}}

    private inner class CommThread : Thread() {

        private val mmInStream: InputStream = mmSocket.inputStream
        private val mmOutStream: OutputStream = mmSocket.outputStream
        private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

        override fun run() {
            var numBytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                // Read from the InputStream.
                numBytes = try {
                    mmInStream.read(mmBuffer)
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream disconnected", e)
                    break
                }
                //TODO: preface responses from pi with code, put in arg2

                // Convert the message to String s.t. the intention of the message can be understood
                val message = mmBuffer.toString(Charset.defaultCharset())

                var readMsg: Message?

                when (message) {
                    "ERROR" ->
                        readMsg = handler.obtainMessage(
                        MESSAGE_ERROR, numBytes, -1,
                        message
                    )
                    "" ->
                        readMsg = handler.obtainMessage(
                        MESSAGE_EMPTY, numBytes, -1,
                        message
                    )
                    else ->
                        readMsg = handler.obtainMessage(
                        MESSAGE_READ, numBytes, -1,
                        message
                    )
                }

                readMsg.sendToTarget()
            }
        }

        // Call this from the main activity to send data to the remote device.
        fun write(bytes: ByteArray) {
            try {
                mmOutStream.write(bytes)
            } catch (e: IOException) {
                Log.e(TAG, "Error occurred when sending data", e)

                // Send a failure message back to the activity.
                val writeErrorMsg = handler.obtainMessage(MESSAGE_TOAST)
                val bundle = Bundle().apply {
                    putString("toast", "Couldn't send data to the other device")
                }
                writeErrorMsg.data = bundle
                handler.sendMessage(writeErrorMsg)
                return
            }

            // Share the sent message with the UI activity.
            val writtenMsg = handler.obtainMessage(
                MESSAGE_WRITE, -1, -1, mmBuffer.toString(Charset.defaultCharset()))
            writtenMsg.sendToTarget()
        }

        // Call this method from the main activity to shut down the connection.
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the connect socket", e)
            }
        }
    }

    private inner class ConnectThread : Thread() {

        override fun run() {
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()

            mmSocket.use { socket ->
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                socket.connect()
            }
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the client socket", e)
            }
        }
    }

    fun connect() {
        try {
            if (mmSocket.isConnected) {
                ConnectThread().start()}
            else{
                handler.obtainMessage(MESSAGE_CONNECT,-1,-1, "Cannot Connect").sendToTarget()
                throw Exception("Socket cannot be connected")
            }
            CommThread().start()
            handler.obtainMessage(MESSAGE_CONNECT,-1,-1, "Connected!").sendToTarget()
        }
        catch (e : Exception) {
            Log.e(TAG, e.message)
        }
    }

    fun disconnect() {
        try {
            CommThread().cancel()
            Thread.sleep(200)
            ConnectThread().cancel()
        }
        catch (e : Exception) {
            Log.e(TAG,e.message)
        }
    }

    private fun write(str: String) {
        try { CommThread().write(str.toByteArray()) }
        catch (e : Exception) { Log.e(TAG,e.message) }
    }

    fun get(vararg args: String) {
        if (args.isNotEmpty()) {
            val str = args.foldRight("") {currentValue, result -> "$currentValue & $result" }.dropLast(3)
            write("GET $str")
        }
        else throw IllegalArgumentException("args cannot be empty")
    }

    fun set(vararg args: String) {
        if (args.isNotEmpty()) {
            val str = args.foldRight("") {currentValue, result -> "$currentValue & $result" }.dropLast(3)
            write("SET $str")
        }
        else throw IllegalArgumentException("args cannot be empty")
    }

    companion object {
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

        fun getAlerts(gasType: String, alertType: String = "predicted"): String {
            return ("$gasType alerts = $alertType")
        }

        fun getStatus(gasType: String): String {
            return ("$gasType status")
        }



        fun setGuidelines(guideline: String = "WHO") : String {
            return ("guideline $guideline")
        }

        fun dateTimeFormatter(time: LocalDateTime) : String {
            return time.format(formatter)
        }
    }
}





