package com.sarbaevartur.android.wywreader

class BookFile(){

    internal lateinit var filename: String
    internal lateinit var path: String

    fun getFilename(): String{
        return filename
    }

    fun getPath(): String{
        return path
    }

    override fun hashCode(): Int {
        return filename.hashCode() + path.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is BookFile) {
            val bookFile = other
            return filename == bookFile.getFilename() && path == bookFile.getPath()
        }
        return false
    }
}