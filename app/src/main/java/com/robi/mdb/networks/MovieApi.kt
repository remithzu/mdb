package com.robi.mdb.networks

import android.content.Context
import com.facebook.shimmer.BuildConfig
import com.google.gson.Gson
import com.robi.mdb.models.Actor
import com.robi.mdb.models.Genre
import com.robi.mdb.models.Movie
import com.robi.mdb.models.MovieDetail
import com.robi.mdb.models.Video
import com.robi.mdb.networks.intercepter.ServerBusyIntercepter
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

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

    class Creator(val context: Context) {
        //@Inject
        fun MovieApi(httpClient: OkHttpClient, gson: Gson): MovieApi {
            val baseUrl = "https://www.googleapis.com/youtube/v3/"
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(providesClientYoutube())
                .build()
            return retrofit.create(MovieApi::class.java)
        }

        private fun providesClientYoutube(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(ServerBusyIntercepter(context))
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
    }
}