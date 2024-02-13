package com.robi.mdb.networks

import android.content.Context
import com.robi.mdb.utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.util.Properties

class ApiProvider {
    companion object {
        fun getInstance() : MovieApi {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val api: MovieApi?
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
            api = retrofit.create(MovieApi::class.java)
            return api
        }
    }
}