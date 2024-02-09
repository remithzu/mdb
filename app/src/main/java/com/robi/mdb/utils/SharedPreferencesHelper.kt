package com.robi.mdb.utils

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {
    fun setLoginCredentials(username: String, credential: String) {
        sharedPreferences.edit {
            putString("username", username)
            putString("credential", credential)
            putBoolean("isLoggedIn", true)
        }

    }

    fun getLoginCredentials(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}