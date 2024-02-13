package com.robi.mdb.repository

import android.database.Observable
import android.util.Log
import com.robi.mdb.models.Actor
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Video
import com.robi.mdb.networks.ApiProvider
import com.robi.mdb.networks.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class MovieRepositoryImpl(): MovieRepository {
    override suspend fun movieDetail(movieId: Int): Response<MovieDetail> {
        return ApiProvider.getInstance().movieDetail(movieId)
    }

    override suspend fun movieActor(actorId: Int): Response<Actor>{
        return ApiProvider.getInstance().actors(actorId)
    }

    override suspend fun movieTrailer(trailerId: Int): Response<Video> {
        return ApiProvider.getInstance().trailer(trailerId)
    }
}