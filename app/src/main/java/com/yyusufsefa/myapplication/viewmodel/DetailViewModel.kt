package com.yyusufsefa.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.Source

class DetailViewModel(application: Application):BaseViewModel(application) {

    val articleLiveData= MutableLiveData<Articles>()

    fun getData(title:String, source: String, time:String, desc:String, imageUrl:String, url:String){

        var article=Articles(Source(null,source),"",title,desc,url,imageUrl,time)

        articleLiveData.value=article
    }


}