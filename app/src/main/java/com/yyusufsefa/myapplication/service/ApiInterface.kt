package com.yyusufsefa.myapplication.service

import com.yyusufsefa.myapplication.model.HeadLines
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<HeadLines>


   @GET("top-headlines?country=tr&apiKey=yourKey")
   fun getHeadlines():Single<HeadLines>

    @GET("top-headlines?country=tr&apiKey=yourKey")
    fun getHead():Call<HeadLines>


}