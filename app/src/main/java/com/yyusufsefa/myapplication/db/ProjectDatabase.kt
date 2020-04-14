package com.yyusufsefa.myapplication.db

import android.content.Context
import androidx.room.*
import androidx.room.TypeConverter
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.Source



@Database(entities = [
    Articles::class,
    Source::class],
    version = 1,
    exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "news-db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}