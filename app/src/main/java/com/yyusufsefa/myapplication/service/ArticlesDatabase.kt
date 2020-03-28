package com.yyusufsefa.myapplication.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yyusufsefa.myapplication.model.Articles


@Database(entities = arrayOf(Articles::class),version = 1)
abstract class ArticlesDatabase :RoomDatabase(){

    abstract fun articleDao():ArticleDao

    companion object{

        @Volatile private var instance:ArticlesDatabase?=null

        private val lock=Any()

        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance?: makeDatabase(context).also {
                instance=it
            }
        }
        private fun makeDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,ArticlesDatabase::class.java,"articledatabase"

        ).build()

    }

}