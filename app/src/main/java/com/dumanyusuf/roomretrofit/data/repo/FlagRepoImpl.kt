package com.dumanyusuf.roomretrofit.data.repo

import com.dumanyusuf.roomretrofit.data.local.FlagDao
import com.dumanyusuf.roomretrofit.data.local.FlagEntity
import com.dumanyusuf.roomretrofit.data.remote.FlagApi
import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.domain.repo.FlagRepo
import com.dumanyusuf.roomretrofit.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FlagRepoImpl @Inject constructor(
    private val api: FlagApi,
    private val dao: FlagDao
) : FlagRepo {
    override fun getFlags(): Flow<Resource<List<Flag>>> = flow {
        emit(Resource.Loading())

        val localFlags = dao.getAllFlags().map { entities ->
            entities.map { entity ->
                Flag(
                    name = entity.name,
                    capital = entity.capital,
                    region = entity.region,
                    currency = entity.currency,
                    flag = entity.flag,
                    language = entity.language
                )
            }
        }

        try {
            val remoteFlags = api.getFlag()
            dao.deleteAllFlags()
            dao.insertFlags(remoteFlags.map { dto ->
                FlagEntity(
                    name = dto.name,
                    capital = dto.capital,
                    region = dto.region,
                    currency = dto.currency,
                    flag = dto.flag,
                    language = dto.language
                )
            })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = localFlags.first()
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = localFlags.first()
            ))
        }

        val newFlags = dao.getAllFlags().map { entities ->
            entities.map { entity ->
                Flag(
                    name = entity.name,
                    capital = entity.capital,
                    region = entity.region,
                    currency = entity.currency,
                    flag = entity.flag,
                    language = entity.language
                )
            }
        }
        emit(Resource.Success(newFlags.first()))
    }
}
