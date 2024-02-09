package com.robi.mdb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robi.mdb.models.BookMark
import com.robi.mdb.models.GenreIds

@Database(entities = [BookMark::class, GenreIds::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun BookMarkDao(): BookMarkDao
    abstract fun GenreIdsDao(): GenreIdsDao
}