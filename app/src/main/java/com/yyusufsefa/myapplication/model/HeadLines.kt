package com.yyusufsefa.myapplication.model

import com.google.gson.annotations.SerializedName

class HeadLines{

    @SerializedName("status")
    val status:String?=null

    @SerializedName("totalResults")
    val totalResults:String?=null

    @SerializedName("articles")
    val articles:List<Articles>?=null


}