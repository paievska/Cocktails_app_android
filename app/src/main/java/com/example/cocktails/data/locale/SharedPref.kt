package com.example.cocktails.data.locale

import android.content.Context
import com.example.cocktails.R

class SharedPref(context: Context) {
    private val prefsName = R.string.preference_file_key
    private val sharedPreferences = context.getSharedPreferences(prefsName.toString(), Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun getOption1(): Boolean {
        return sharedPreferences.getBoolean("option1", false)
    }

    fun setOption1(value: Boolean) {
        with(editor) {
            putBoolean("option1", value)
            apply()
        }
    }

    fun getOption2(): Boolean {
        return sharedPreferences.getBoolean("option2", false)
    }

    fun setOption2(value: Boolean) {
        with(editor) {
            putBoolean("option2", value)
            apply()
        }
    }

    fun getOption3(): Boolean {
        return sharedPreferences.getBoolean("option3", false)
    }

    fun setOption3(value: Boolean) {
        with(editor) {
            putBoolean("option3", value)
            apply()
        }
    }
}