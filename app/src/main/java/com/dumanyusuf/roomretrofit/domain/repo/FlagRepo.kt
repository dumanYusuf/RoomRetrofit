package com.dumanyusuf.roomretrofit.domain.repo

import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlagRepo {
    fun getFlags(): Flow<Resource<List<Flag>>>
}