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

    fun saveIntData(key: String, data: Int) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(key, data).apply()
    }

    fun getIntData(key: String): Int {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }

    fun saveStringSetData(key: String, data: Set<String>) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putStringSet(key, data).apply()
    }


    fun getStringSetData(key: String): Set<String>? {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(key, null)
    }
}