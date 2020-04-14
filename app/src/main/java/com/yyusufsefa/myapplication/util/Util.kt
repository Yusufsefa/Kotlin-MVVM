package com.yyusufsefa.myapplication.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yyusufsefa.myapplication.R

// useful ext. fun. for hide/show view component

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun ImageView.downloadFromUrl(url:String?,progressDrawable:CircularProgressDrawable){

    val options=RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context?):CircularProgressDrawable{
    return CircularProgressDrawable(context!!).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}


@BindingAdapter("android.downloadUrl")
fun downloadImage(view:ImageView,url:String){
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}