package com.yyusufsefa.myapplication.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yyusufsefa.myapplication.model.Articles

@Dao
interface ArticleDao {

    @Insert
    suspend fun insertAll(vararg articles: Articles):List<Long>

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles():List<Articles>

    @Query("SELECT * FROM articles WHERE uuid = :id")
    suspend fun getArticle(id:Int):Articles

    @Query("DELETE FROM articles")
    suspend fun deleteAll()



}