package com.yyusufsefa.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Articles(

    @SerializedName("source")
    val source: Source? = null,

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?
) : Parcelable{
    /**
     * // If you dont use the room,u should remove that annotation
     * @PrimaryKey(autoGenerate = true)
     */
    @IgnoredOnParcel
    var uuid: Int = 0
}




