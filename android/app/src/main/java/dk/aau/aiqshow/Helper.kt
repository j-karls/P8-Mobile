package dk.aau.aiqshow

fun ByteArrayToString(ba : ByteArray, size: Int) : String {
    return ba.slice(0 until size).toByteArray().toString(Charsets.UTF_8)
}