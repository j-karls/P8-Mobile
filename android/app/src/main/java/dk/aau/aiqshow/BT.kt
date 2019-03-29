package dk.aau.aiqshow

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

private const val TAG = "MY_APP_DEBUG_TAG"

// Defines several constants used when transmitting messages between the
// service and the UI.
const val MESSAGE_READ: Int = 0
const val MESSAGE_WRITE: Int = 1
const val MESSAGE_TOAST: Int = 2
// ... (Add other message types here as needed.)

class MyBluetoothService(
    // handler that gets info from Bluetooth service
    private val handler: Handler) {

    private lateinit var succ: BluetoothSocket

    inner class ConnectedThread : Thread() {

        private val mmInStream: InputStream = succ.inputStream
        private val mmOutStream: OutputStream = succ.outputStream
        private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

        override fun run() {
            var numBytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                // Read from the InputStream.
                numBytes = try {
                    mmInStream.read(mmBuffer)
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream was disconnected", e)
                    break
                }

                // Send the obtained bytes to the UI activity.
                val readMsg = handler.obtainMessage(
                    MESSAGE_READ, numBytes, -1,
                    mmBuffer)
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
                MESSAGE_WRITE, -1, -1, mmBuffer)
            writtenMsg.sendToTarget()
        }

        // Call this method from the main activity to shut down the connection.
        fun cancel() {
            try {
                succ.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the connect socket", e)
            }
        }
    }

    inner class ConnectThread(device: BluetoothDevice) : Thread() {

        /*private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee"))
        }*/

        private val mmSocket : BluetoothSocket? = device.javaClass.getMethod("createRfcommSocket", (Int::class.javaPrimitiveType))
                .invoke(device,1) as BluetoothSocket


        public override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            succ = mmSocket as BluetoothSocket

            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()




            succ.use { socket ->
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                socket.connect()

                // The connection attempt succeeded. Perform work associated with
                // the connection in a separate thread.
                this@MyBluetoothService.ConnectedThread().run()
            }
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                succ.close()
            } catch (e: IOException) {
                Log.e(dk.aau.aiqshow.TAG, "Could not close the client socket", e)
            }
        }
    }
    fun stuff() {
    }
}