package com.yyusufsefa.myapplication.viewmodel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.myapplication.db.ProjectDao
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.service.ApiInterface
import com.yyusufsefa.myapplication.service.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class HomeViewModel : BaseViewModel() {



    lateinit var projectDao: ProjectDao

    init {
        refreshData()
    }

    fun refreshData(){
        getDataFromAPI()
    }


    private fun getDataFromAPI() {

        ApiService.getClient()
            .create(ApiInterface::class.java)
            .getHead()
            .enqueue(object : retrofit2.Callback<HeadLines> {
                override fun onFailure(call: Call<HeadLines>, t: Throwable) {
                    Log.e("onFailure", t.localizedMessage ?: "Empty")
                }
                override fun onResponse(call: Call<HeadLines>, response: Response<HeadLines>) {

                    viewModelScope.launch {
                        projectDao.insertArticles(response.body()?.articles)
                    }

                }
            })
    }


}