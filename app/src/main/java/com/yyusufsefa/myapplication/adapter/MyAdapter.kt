package com.yyusufsefa.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.model.Articles

class MyAdapter(val articles: ArrayList<Articles>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(var view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    fun updateList(newArticles:List<Articles>){
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}