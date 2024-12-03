package com.dumanyusuf.roomretrofit.domain.use_case

import android.util.Log
import com.dumanyusuf.roomretrofit.data.remote.dto.toFlag
import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.domain.repo.FlagRepo
import com.dumanyusuf.roomretrofit.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlagUseCase @Inject constructor(private val repo: FlagRepo) {

    fun getFlagList(): Flow<Resource<List<Flag>>> = flow {
        try {
            emit(Resource.Loading())
            Log.e("FlagUseCase", "Loading started")

            val flagList = repo.getFlagList()
            Log.e("FlagUseCase", "Fetched flag list from repo: $flagList")

            if (flagList.isNotEmpty()) {
                val mappedFlags = flagList.toFlag()
                Log.e("FlagUseCase", "Mapped flags: $mappedFlags")

                emit(Resource.Success(mappedFlags))
            } else {
                Log.e("FlagUseCase", "Flag list is empty")
                emit(Resource.Error("Error: Empty list"))
            }
        } catch (e: Exception) {
            Log.e("FlagUseCase", "Exception occurred: ${e.message}", e)
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}
