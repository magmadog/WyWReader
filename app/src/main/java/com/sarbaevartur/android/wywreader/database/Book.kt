package com.sarbaevartur.android.wywreader.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Book(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    val path: String = "/",
    var library: String = "ALLBOOKS",
    val bookname: String = "",
    val author: String = "",
    var read: Int = 0
) {
}