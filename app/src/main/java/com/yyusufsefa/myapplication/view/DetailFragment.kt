package com.yyusufsefa.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.databinding.FragmentDetailBinding
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var articles: Articles

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articles = DetailFragmentArgs.fromBundle(it).newsArticle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        //viewModel.getDataFromRoom(uuid)
        viewModel.getData(articles)
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                dataBinding.article = articles

                webView.settings.domStorageEnabled = true
                webView.settings.javaScriptEnabled = true
                webView.settings.loadsImagesAutomatically = true
                webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                webView.webViewClient = WebViewClient()
                webView.loadUrl(articles.url)
                if (webView.isShown) {
                    loader.visibility = View.INVISIBLE
                }
            }

        })
    }
}
