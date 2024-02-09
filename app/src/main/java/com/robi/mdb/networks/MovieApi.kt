package com.robi.mdb.networks

import com.facebook.shimmer.BuildConfig
import com.robi.mdb.models.Actor
import com.robi.mdb.models.Genre
import com.robi.mdb.models.Movie
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie")
    suspend fun discoverPopular (
        @Query("page") page: Int,
        @Query("sort_by") popular: String = "popularity.desc",
//        @Query("api_key") apiKey: String =
    ): Response<Movie>

    @GET("/3/search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        //@Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<Movie>

    @GET("/3/movie/{id}")
    suspend fun movieDetail (
        @Path("id") id: Int,
        //@Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieDetail>

    @GET("/3/genre/movie/list")
    suspend fun genreList (
        //@Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<ArrayList<Genre>>

    @GET("/3/movie/{id}/credits")
    suspend fun actors (
        @Path("id") id: Int,
        //@Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<Actor>

    @GET("/3/movie/{id}/videos")
    suspend fun trailer (
        @Path("id") id: Int,
        //@Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<Video>
}