package com.yyusufsefa.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.databinding.FragmentHomeBindingImpl
import com.yyusufsefa.myapplication.databinding.ItemsBinding
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.util.downloadFromUrl
import com.yyusufsefa.myapplication.util.placeholderProgressBar
import com.yyusufsefa.myapplication.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.items.view.*
import kotlinx.android.synthetic.main.items.view.tvDate
import kotlinx.android.synthetic.main.items.view.tvSource
import kotlinx.android.synthetic.main.items.view.tvTitle

class MyAdapter(val articles: ArrayList<Articles>):RecyclerView.Adapter<MyAdapter.ViewHolder>(),ArticleListener {

    class ViewHolder(var view: ItemsBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)

        //val view=inflater.inflate(R.layout.items,parent,false)

        val view=DataBindingUtil.inflate<ItemsBinding>(inflater,R.layout.items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.view.article=articles[position]
        holder.view.onListener=this
    }

    fun updateList(newArticles:List<Articles>){
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun onArticleClicked(v: View) {

        val uuid=v.uuidText.text.toString().toInt()

        val action=HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            uuid,
            v.tvTitle.text.toString(),
            v.tvSource.text.toString(),
            v.tvDate.text.toString(),
            v.tvDescp.text.toString(),
            v.tvimageUrl.text.toString(),
            v.url.text.toString())
        Navigation.findNavController(v).navigate(action)
    }
}