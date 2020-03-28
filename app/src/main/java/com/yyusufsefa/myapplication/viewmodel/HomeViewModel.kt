package com.yyusufsefa.myapplication.viewmodel

import android.app.Application
import android.widget.HeaderViewListAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.service.ApiInterface
import com.yyusufsefa.myapplication.service.ApiService
import com.yyusufsefa.myapplication.service.ArticlesDatabase
import com.yyusufsefa.myapplication.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.sql.SQLOutput
import java.util.*

class HomeViewModel(application: Application):BaseViewModel(application) {

    private val articlesApi=ApiService()
    private val disposable=CompositeDisposable()
    private val headLines=MutableLiveData<HeadLines>()
    private var customSharedPreferences=CustomSharedPreferences(getApplication())

    private var refreshTime=10*60*1000*1000L

    val articles=MutableLiveData<List<Articles>>()


    fun refreshData(){

        val updateTime=customSharedPreferences.getTime()

        if(updateTime!=null && updateTime!=0L && System.nanoTime()-updateTime<refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }

        getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        launch {
            val article=ArticlesDatabase(getApplication()).articleDao().getAllArticles()
            showArticle(article)
            Toast.makeText(getApplication(),"Sql",Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromAPI(){

        ApiService.getClient().create(ApiInterface::class.java).getHead()
            .enqueue(object :retrofit2.Callback<HeadLines>{
                override fun onFailure(call: Call<HeadLines>, t: Throwable) {

                }
                override fun onResponse(call: Call<HeadLines>, response: Response<HeadLines>) {

                    var article=response.body()?.articles
                    if (article != null) {
                        inSQLite(article)
                    }
                }

            })
    }

    private fun showArticle(a: List<Articles>){
        articles.value=a
    }

    private fun inSQLite(list:List<Articles>){
        launch {
            val dao=ArticlesDatabase(getApplication()).articleDao()
            dao.deleteAll()
            val listLong=dao.insertAll(*list.toTypedArray())
            var i=0
            while(i<list.size){
                list[i].uuid=listLong[i].toInt()
                i++
            }
            showArticle(list)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }


}