package com.dumanyusuf.roomretrofit.domain.repo

import com.dumanyusuf.roomretrofit.data.remote.dto.FlagDto


interface FlagRepo {

    suspend fun getFlagList():FlagDto

}