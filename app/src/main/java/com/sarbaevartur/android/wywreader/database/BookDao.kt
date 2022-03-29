package com.sarbaevartur.android.wywreader.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id =(:id)")
    fun getBook(id: UUID): LiveData<Book?>
}