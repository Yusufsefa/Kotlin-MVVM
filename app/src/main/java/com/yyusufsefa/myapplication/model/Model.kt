package com.yyusufsefa.myapplication.model

import com.google.gson.annotations.SerializedName

class Articles{

    @SerializedName("source")
    var source:Source?=null

    @SerializedName("author")
    val author:String?=null

    @SerializedName("title")
    val title:String?=null

    @SerializedName("description")
    val description:String?=null

    @SerializedName("url")
    val url:String?=null

    @SerializedName("urlToImage")
    val urlToImage:String?=null

    @SerializedName("publishedAt")
    val publishedAt:String?=null

}