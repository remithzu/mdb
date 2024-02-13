package com.robi.mdb.networks.intercepter

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit

class ServerBusyIntercepter (val context: Context) /*: Interceptor*/ {

    /*private var retrofit: Retrofit? = null
    private var busyDialog: ServerBusySheetDialog = ServerBusySheetDialog()

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        val request = chain.request()
        val response = chain.proceed(request)

        val whitelist: Array<String> = arrayOf(

        )

        val _url = url.encodedPath()
        when {
            response.code() in 503..505 -> {
                val match = whitelist.filter { _url.contains(it, true) }
                if (match.isEmpty()) {
                    RxBus.publish(ServerBusySheetDialog())
                }
            }
            response.code in 500..502 -> {
                val match = whitelist.filter { _url.contains(it, true) }
                if (match.isEmpty()){
                    RxBus.publish(ApiFailureDialog())
                }
            }
        }
        return response
    }

    private inline fun <reified T> getError(body: ResponseBody?): T? = body?.let {
        retrofit?.responseBodyConverter<T>(T::class.java, T::class.java.annotations)?.convert(it)
    }*/
}