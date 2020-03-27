package com.yyusufsefa.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yyusufsefa.myapplication.model.Articles

class DetailViewModel:ViewModel() {

    val articleLiveData=MutableLiveData<Articles>()


}