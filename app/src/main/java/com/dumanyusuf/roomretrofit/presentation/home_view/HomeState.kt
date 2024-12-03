package com.dumanyusuf.roomretrofit.presentation.home_view

import com.dumanyusuf.roomretrofit.domain.model.Flag

data class HomeState(
    val flaglist:List<Flag> =emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
