package com.yyusufsefa.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Source(
    @SerializedName("id")
    val id:String?,

    @SerializedName("name")
    val name:String?
) : Parcelable