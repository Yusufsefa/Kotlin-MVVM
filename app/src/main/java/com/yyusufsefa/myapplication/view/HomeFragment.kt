package com.yyusufsefa.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yyusufsefa.myapplication.adapter.MyAdapter
import com.yyusufsefa.myapplication.databinding.FragmentHomeBinding
import com.yyusufsefa.myapplication.util.hide
import com.yyusufsefa.myapplication.util.show
import com.yyusufsefa.myapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MyAdapter

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Try to use databinding everyscreen
        binding = FragmentHomeBinding.inflate(layoutInflater, null, false)

        // Click event with higher-order fun. looking more pretty :),
        adapter = MyAdapter(arrayListOf()) { articles ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(articles)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)
            .get(HomeViewModel::class.java) //hangi fragmenttayız ve hangi viewwmodel kullanacağız
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            binding.recyclerView.hide()
            // i did not understand why are you send the same request twice
            viewModel.refreshData()
            binding.swipeRefresh.isRefreshing = false
            viewModel.refreshFromAPI()
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                binding.recyclerView.show()
                adapter.updateList(articles)
            }
        })
    }
}
