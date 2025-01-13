package com.dumanyusuf.roomretrofit.domain.use_case

import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.domain.repo.FlagRepo
import com.dumanyusuf.roomretrofit.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlagUseCase @Inject constructor(
    private val repository: FlagRepo
) {
    operator fun invoke(): Flow<Resource<List<Flag>>> {
        return repository.getFlags()
    }
}
