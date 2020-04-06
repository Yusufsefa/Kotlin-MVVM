package com.yyusufsefa.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.service.ApiInterface
import com.yyusufsefa.myapplication.service.ApiService
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(application: Application):BaseViewModel(application) {

    val articles= MutableLiveData<List<Articles>>()

    fun refreshData(){

        getDataFromAPI()

    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    fun getDataFromAPI(){

        ApiService.getClient().create(ApiInterface::class.java).getHead()
            .enqueue(object :retrofit2.Callback<HeadLines>{
                override fun onFailure(call: Call<HeadLines>, t: Throwable) {

                }
                override fun onResponse(call: Call<HeadLines>, response: Response<HeadLines>) {

                    var article=response.body()?.articles
                    if (article != null) {
                        articles.value=article
                    }
                }

            })
    }


}