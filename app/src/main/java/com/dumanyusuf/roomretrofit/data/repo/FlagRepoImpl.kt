package com.dumanyusuf.roomretrofit.data.repo

import com.dumanyusuf.roomretrofit.data.remote.FlagApi
import com.dumanyusuf.roomretrofit.data.remote.dto.FlagDto
import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.domain.repo.FlagRepo
import javax.inject.Inject

class FlagRepoImpl @Inject constructor(private val api: FlagApi):FlagRepo {

    override suspend fun getFlagList(): FlagDto {
        return api.getFlag()
    }
}