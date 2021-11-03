package com.sarbaevartur.android.wywreader

import androidx.lifecycle.ViewModel
import android.os.Environment
import android.util.Log
import java.io.File


class BookListViewModel: ViewModel() {

    val books = mutableListOf<BookFile>()

    init {
        for (i in 0 until 30){
            val book = BookFile()
            book.filename = "test $i"
            book.path = "path/$i"
            books += book
        }

        val path = Environment.getExternalStorageDirectory().toString()
        Log.d("Files", "Path: $path")
        val directory = File(path)
        val files: Array<File> = directory.listFiles()!!
        Log.d("Files", "Size: " + files.size)
        for (i in files.indices) {
            Log.d("Files", "FileName:" + files[i].getName())
        }
    }
}