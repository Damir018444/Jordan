package com.example.jordan

import android.content.Context
import io.ktor.utils.io.concurrent.shared

class SharedPreferenceHelper(private val context: Context) {
    companion object{
        private const val MY_PREF_KEY = "MY_PREF"
    }

    fun saveStringData(key: String, data: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getStringData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}