package com.robi.mdb.repository

import android.database.Observable
import com.robi.mdb.models.Actor
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Video
import retrofit2.Response

interface MovieRepository {
    suspend fun movieDetail(movieId: Int): Response<MovieDetail>
    suspend fun movieActor(actorId: Int): Response<Actor>
    suspend fun movieTrailer(trailerId: Int): Response<Video>
}