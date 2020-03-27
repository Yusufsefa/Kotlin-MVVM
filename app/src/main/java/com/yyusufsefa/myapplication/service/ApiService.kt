package com.yyusufsefa.myapplication.service

import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.HeadLines
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL="http://newsapi.org/v2/"

    companion object{

        fun getClient():Retrofit{
            return Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)


    fun getData():Single<HeadLines>{
        return api.getHeadlines()
    }







}