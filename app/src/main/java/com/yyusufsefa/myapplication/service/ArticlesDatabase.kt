package com.yyusufsefa.myapplication.service

import android.content.Context
import androidx.room.*
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.Source


@Database(entities = [Articles::class,Source::class],version = 1)
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

object Converter {



}



