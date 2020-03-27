package com.yyusufsefa.myapplication.viewmodel

import android.widget.HeaderViewListAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.service.ApiInterface
import com.yyusufsefa.myapplication.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import java.util.*

class HomeViewModel:ViewModel() {

    private val articlesApi=ApiService()
    private val disposable=CompositeDisposable()
    private val headLines=MutableLiveData<HeadLines>()

    val articles=MutableLiveData<List<Articles>>()


    fun refreshData(){

        getDataFromAPI()
    }

    fun getDataFromAPI(){

        ApiService.getClient().create(ApiInterface::class.java).getHead()
            .enqueue(object :retrofit2.Callback<HeadLines>{
                override fun onFailure(call: Call<HeadLines>, t: Throwable) {

                }

                override fun onResponse(call: Call<HeadLines>, response: Response<HeadLines>) {

                    articles.value= response.body()?.articles
                }

            })

    }

}