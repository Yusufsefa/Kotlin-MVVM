package com.yyusufsefa.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Source(

    @ColumnInfo(name="id")
    @SerializedName("id")
    val id:String?,

    @ColumnInfo(name="name")
    @SerializedName("name")
    val name:String?

) {

}