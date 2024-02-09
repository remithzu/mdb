package com.robi.mdb.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.robi.mdb.models.BookMark

@Dao
interface BookMarkDao {
    @Query("SELECT * FROM bookmark")
    fun getListBookmark(): List<BookMark>

    @Insert
    fun insertBookmark(vararg bookmark: BookMark)

    @Update
    fun updateBookmark(vararg bookmark: BookMark)

    @Delete
    fun deleteBookmark(vararg bookmark: BookMark)
}