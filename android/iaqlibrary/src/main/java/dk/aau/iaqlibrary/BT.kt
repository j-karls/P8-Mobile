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


private const val TAG = "BLUETOOTH_SERVICE_DEBUG"
private const val uuid = "94f39d29-7d6d-437d-973b-fba39e49d4ee"
private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH.mm.ss")


class MyBluetoothService( private val handler: Handler, device: BluetoothDevice) {

    private val _socket: BluetoothSocket =
        try {device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)}
        catch (e: Exception){
            throw IOException("FUCK")
            /*try {device.javaClass.getMethod("createRfcommSocket", (Int::class.javaPrimitiveType))
                .invoke(device,1) as BluetoothSocket }
            catch (e: Exception) {Log.e(TAG, e.toString())
                throw Exception("No bluetooth connection could be created")}*/
        }



    private inner class CommThread : Thread() {

        private val _inStream: InputStream = _socket.inputStream
        private val _outStream: OutputStream = _socket.outputStream
        private val _buffer: ByteArray = ByteArray(1024) // _buffer store for the stream

        override fun run() {
            var numBytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                // Read from the InputStream.
                numBytes = try {
                    _inStream.read(_buffer)
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream disconnected", e)
                    break
                }
                //TODO: preface responses from pi with code, put in arg2

                // Convert the message to String s.t. the intention of the message can be understood
                val buff : ByteArray = _buffer.clone().take(numBytes).toByteArray()
                val message = buff.toString()

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

        fun write(bytes: ByteArray) {
            try {
                _outStream.write(bytes)
            } catch (e: IOException) {
                Log.e(TAG, "Error occurred when sending data", e)

                val writeErrorMsg = handler.obtainMessage(MESSAGE_TOAST)
                val bundle = Bundle().apply {
                    putString("toast", "Couldn't send data to the other device")
                }
                writeErrorMsg.data = bundle
                handler.sendMessage(writeErrorMsg)
                return
            }

            val writtenMsg = handler.obtainMessage(
                MESSAGE_WRITE, -1, -1, bytes.toString(Charset.defaultCharset()))
            writtenMsg.sendToTarget()
        }

        fun cancel() {
            try {
                _socket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the connect socket", e)
            }
        }
    }

    private inner class ConnectThread : Thread() {

        override fun run() {
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            try {
                _socket.use { socket ->
                    socket.connect()

                    this@MyBluetoothService.CommThread().start()
                }
            }
            catch (e: Exception) {
                Log.e(TAG,e.toString())
            }
            Log.i(TAG,"FUCKSHITUP")
        }

        fun cancel() {
            try {
                _socket.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the client socket", e)
            }
        }
    }

    private fun write(str: String) {
        try { CommThread().write(str.toByteArray()) }
        catch (e : Exception) { Log.e(TAG,e.message) }
    }

    fun connect() {

        try { ConnectThread().start() }
        catch (e : Exception) {
            Log.e(TAG, e.message)
        handler.obtainMessage(MESSAGE_CONNECT,-1,-1, "Cannot Connect").sendToTarget()
        throw Exception("Socket cannot be connected")
        }
        /*Thread.sleep(200)
        Log.i(TAG,_socket.remoteDevice.name)
        CommThread().start()*/
    }

    fun disconnect() {
        try {
            CommThread().interrupt()
            CommThread().cancel()
            Thread.sleep(200)
            ConnectThread().interrupt()
            ConnectThread().cancel()
        }
        catch (e : Exception) {
            Log.e(TAG,e.message)
        }
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
        const val MESSAGE_READ: Int = 0
        const val MESSAGE_WRITE: Int = 1
        const val MESSAGE_TOAST: Int = 2
        const val MESSAGE_CONNECT: Int = 3
        const val MESSAGE_EMPTY: Int = 4
        const val MESSAGE_ERROR: Int = 5

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