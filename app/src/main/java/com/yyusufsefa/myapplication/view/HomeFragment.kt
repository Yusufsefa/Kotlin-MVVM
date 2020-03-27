package com.yyusufsefa.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.adapter.MyAdapter
import com.yyusufsefa.myapplication.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    private lateinit var viewModel:HomeViewModel
    private val adapter=MyAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java) //hangi fragmenttayız ve hangi viewwmodel kullanacağız
        viewModel.refreshData()

        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter

        swipeRefresh.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            viewModel.refreshData()
            swipeRefresh.isRefreshing=false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.articles.observe(viewLifecycleOwner, Observer {articles->

            articles?.let {
                recyclerView.visibility=View.VISIBLE
                adapter.updateList(articles)
            }
        })

    }


}
