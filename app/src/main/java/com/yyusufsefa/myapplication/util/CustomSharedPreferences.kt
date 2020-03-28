package com.yyusufsefa.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object{

        private val TIME="preferences_time"
        private var sharedPreferences:SharedPreferences?=null

        @Volatile private var instance:CustomSharedPreferences?=null
        private val lock=Any()

        operator fun invoke(context: Context):CustomSharedPreferences= instance?: synchronized(lock){
            instance?: makeSharedPrefereces(context).also {
                instance=it
            }
        }

        private fun makeSharedPrefereces(context: Context):CustomSharedPreferences{
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong(TIME,time)
        }
    }

    fun getTime()= sharedPreferences?.getLong(TIME,0)

}