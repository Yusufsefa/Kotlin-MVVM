package com.yyusufsefa.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.databinding.ItemsBinding
import com.yyusufsefa.myapplication.model.Articles

class MyAdapter(
    val articles: ArrayList<Articles>,
    val onClickListener: (Articles) -> Unit
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(var view: ItemsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        //val view=inflater.inflate(R.layout.items,parent,false)

        val view = DataBindingUtil.inflate<ItemsBinding>(inflater, R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.article = articles[position]
        holder.itemView.setOnClickListener {
            onClickListener(articles[position])
        }
    }

    fun updateList(newArticles: List<Articles>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}