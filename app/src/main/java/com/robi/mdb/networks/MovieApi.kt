package com.robi.mdb.networks

import android.content.Context
import com.google.gson.Gson
import com.robi.mdb.models.Actor
import com.robi.mdb.models.Genre
import com.robi.mdb.models.Movie
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Video
import com.robi.mdb.utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie")
    suspend fun discoverPopular (
        @Query("page") page: Int,
        @Query("sort_by") popular: String = "popularity.desc",
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<Movie>

    @GET("/3/search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<Movie>

    @GET("/3/movie/{id}")
    suspend fun movieDetail (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<MovieDetail>

    @GET("/3/genre/movie/list")
    suspend fun genreList (
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<ArrayList<Genre>>

    @GET("/3/movie/{id}/credits")
    suspend fun actors (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<Actor>

    @GET("/3/movie/{id}/videos")
    suspend fun trailer (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = Const.KEY
    ): Response<Video>

    class Creator(val context: Context) {
        //@Inject
        fun getInstance() : MovieApi {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val api: MovieApi?
            val client = OkHttpClient.Builder()
            val retrofit = Retrofit
                .Builder()
                .baseUrl(Const.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(MovieApi::class.java)
        }
    }
}