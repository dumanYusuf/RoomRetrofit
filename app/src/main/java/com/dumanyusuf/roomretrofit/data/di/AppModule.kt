package com.dumanyusuf.roomretrofit.data.di

import android.app.Application
import androidx.room.Room
import com.dumanyusuf.roomretrofit.data.local.FlagDatabase
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
    fun provideFlagApi(): FlagApi {
        return Retrofit.Builder()
            .baseUrl(Constans.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlagApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlagDatabase(app: Application): FlagDatabase {
        return Room.databaseBuilder(
            app,
            FlagDatabase::class.java,
            "flag_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFlagRepository(api: FlagApi, db: FlagDatabase): FlagRepo {
        return FlagRepoImpl(api, db.dao)
    }
}