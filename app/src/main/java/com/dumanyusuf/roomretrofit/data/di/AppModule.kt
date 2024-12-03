package com.dumanyusuf.roomretrofit.data.di

import com.dumanyusuf.roomretrofit.data.remote.FlagApi
import com.dumanyusuf.roomretrofit.data.repo.FlagRepoImpl
import com.dumanyusuf.roomretrofit.domain.repo.FlagRepo
import com.dumanyusuf.roomretrofit.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun flagRetrofitProvides():FlagApi{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASEURL).build().create(FlagApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepo(api:FlagApi):FlagRepo{
        return FlagRepoImpl(api)
    }






}