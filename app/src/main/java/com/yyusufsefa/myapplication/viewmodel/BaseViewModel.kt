package com.yyusufsefa.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// in this case, normal viewmodel is okey for you ?


abstract class BaseViewModel : ViewModel() {

    /**
     * You no need to do that viewModel already has own viewModelScope [viewModelScope]by the way you no need to cancel job , its self-canceling
     */

    private fun testViewModelScope() {
        viewModelScope.launch(Dispatchers.Main) {

        }
    }


}