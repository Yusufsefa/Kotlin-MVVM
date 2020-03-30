package com.yyusufsefa.myapplication.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.squareup.picasso.Picasso

import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.items.*
import kotlinx.android.synthetic.main.items.tvDate
import kotlinx.android.synthetic.main.items.tvSource
import kotlinx.android.synthetic.main.items.tvTitle
import kotlinx.android.synthetic.main.items.view.*

class DetailFragment : Fragment() {

    private var uuid=0

    private lateinit var viewModel:DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)


        arguments.let {


        }
    }

    private fun observerLiveData(){
        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer { article->
            article?.let {
                tvTitle.text=article.title
                tvSource.text=article.source?.name.toString()
                tvDate.text=article.publishedAt
                tvDesc.text=article.description

                var imageUrl=article.urlToImage

                Picasso.with(context).load(imageUrl).into(imageView,object :com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG,"succcess")
                    }

                    override fun onError() {
                        Log.d(ContentValues.TAG,"error")

                    }

                })

            }

        })
    }


}
