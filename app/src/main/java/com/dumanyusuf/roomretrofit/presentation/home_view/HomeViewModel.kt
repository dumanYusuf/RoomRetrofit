package com.dumanyusuf.roomretrofit.presentation.home_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.roomretrofit.domain.use_case.FlagUseCase
import com.dumanyusuf.roomretrofit.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val flagUseCase: FlagUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getFlags()
    }

    private fun getFlags() {
        flagUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(
                        flags = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "An unexpected error occurred",
                        flags = result.data ?: emptyList()
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}