package com.robi.mdb.networks

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.util.Properties

object ApiKeyProvider {
    private const val API_KEY_FILE = "endpoint.properties"

    fun getApiKey(context: Context): String? {
        return try {
            val properties = Properties()
            val inputStream: InputStream = context.assets.open(API_KEY_FILE)
            properties.load(inputStream)
            properties.getProperty("api_key")
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}