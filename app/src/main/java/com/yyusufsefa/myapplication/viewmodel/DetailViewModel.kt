package com.yyusufsefa.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.Source

class DetailViewModel:BaseViewModel() {

    val articleLiveData= MutableLiveData<Articles>()

    fun getData(articles: Articles){
       
        articleLiveData.value=articles
    }
}
