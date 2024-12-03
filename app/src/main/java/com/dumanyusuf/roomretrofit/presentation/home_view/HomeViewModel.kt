package com.dumanyusuf.roomretrofit.presentation.home_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.roomretrofit.domain.use_case.FlagUseCase
import com.dumanyusuf.roomretrofit.util.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: FlagUseCase):ViewModel() {


    private val _state= MutableStateFlow<HomeState>(HomeState())
    val state:StateFlow<HomeState> = _state

    init {
        getFlag()
    }


    fun getFlag(){
        viewModelScope.launch (Dispatchers.IO){
            _state.value=HomeState(isLoading = true)
            useCase.getFlagList().onEach {
                when(it){
                    is Resource.Success->{
                        _state.value=HomeState(flaglist = it.data?: emptyList())
                        Log.e("success","veriler basarılı bir şekilde geldi")
                    }
                    is Resource.Loading->{
                        _state.value=HomeState(isLoading = true)
                        Log.e("loading","loading")
                    }
                    is Resource.Error->{
                        _state.value=HomeState(isError = "hata cıktı ${it.message}")
                        Log.e("hata","veriler basarısız ${it.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}