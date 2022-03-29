package com.sarbaevartur.android.wywreader.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Book::class], version = 1)
@TypeConverters(BookTypeConverters::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}