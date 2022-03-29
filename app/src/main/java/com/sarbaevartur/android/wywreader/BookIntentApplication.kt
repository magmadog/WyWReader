package com.sarbaevartur.android.wywreader

import android.app.Application
import com.sarbaevartur.android.wywreader.database.BookRepository

class BookIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BookRepository.initialize(this)
    }
}