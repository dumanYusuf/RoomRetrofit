package com.dumanyusuf.roomretrofit.presentation.home_view

import com.dumanyusuf.roomretrofit.domain.model.Flag

data class HomeState(
    val isLoading: Boolean = false,
    val flags: List<Flag> = emptyList(),
    val error: String = ""
)
