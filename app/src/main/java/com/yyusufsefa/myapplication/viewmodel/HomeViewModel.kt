package com.yyusufsefa.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yyusufsefa.myapplication.model.Articles

class HomeViewModel:ViewModel() {

    val articles=MutableLiveData<List<Articles>>()
    val error=MutableLiveData<Boolean>()
    val loading=MutableLiveData<Boolean>()

    fun refreshData(){

        val article=Articles(null,"yusuf","sefa","yusufsefa",null,"","")

        val articleList= arrayListOf<Articles>(article)
        articles.value=articleList
        error.value=false
        loading.value=false
    }


}