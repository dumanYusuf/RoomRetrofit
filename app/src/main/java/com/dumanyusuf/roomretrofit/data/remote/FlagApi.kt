package com.dumanyusuf.roomretrofit.data.remote

import com.dumanyusuf.roomretrofit.data.remote.dto.FlagDto
import retrofit2.http.GET


interface FlagApi {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    suspend fun getFlag():FlagDto

}