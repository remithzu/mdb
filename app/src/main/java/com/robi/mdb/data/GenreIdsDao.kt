package com.robi.mdb.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.robi.mdb.models.GenreIds

@Dao
interface GenreIdsDao {
    @Query("SELECT * FROM genre_ids WHERE movie_id=:id")
    fun getListGenre(id: Int): List<GenreIds>

    @Insert
    fun insertGenre(vararg genre: GenreIds)

    @Update
    fun updateGenre(vararg genre: GenreIds)

    @Delete
    fun deleteGenre(vararg genre: GenreIds)
}