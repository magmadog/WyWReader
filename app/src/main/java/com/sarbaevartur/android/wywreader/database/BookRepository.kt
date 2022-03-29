package com.sarbaevartur.android.wywreader.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

private const val DATABASE_NAME = "book-database"

class BookRepository private constructor(context: Context){

    private val databse : BookDatabase = Room.databaseBuilder(
        context.applicationContext,
        BookDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val bookDao = databse.bookDao()

    fun getBooks(): LiveData<List<Book>> = bookDao.getBooks()
    fun getBook(id: UUID) : LiveData<Book?> = bookDao.getBook(id)

    companion object{
        private var INSTANCE: BookRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = BookRepository(context)
            }
        }

        fun get(): BookRepository{
            return INSTANCE ?:
            throw IllegalStateException("BookRepository must be initialized")
        }
    }
}