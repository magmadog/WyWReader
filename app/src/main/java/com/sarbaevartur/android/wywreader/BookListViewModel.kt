package com.sarbaevartur.android.wywreader

import androidx.lifecycle.ViewModel
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import com.sarbaevartur.android.wywreader.database.BookRepository
import java.io.File


class BookListViewModel: ViewModel() {

    private val crimeRepository = BookRepository.get()
    val bookListLiveData = crimeRepository.getBooks()

    init {

    }
}