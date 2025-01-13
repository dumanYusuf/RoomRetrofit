package com.dumanyusuf.roomretrofit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlags(flags: List<FlagEntity>)

    @Query("SELECT * FROM flags")
    fun getAllFlags(): Flow<List<FlagEntity>>

    @Query("DELETE FROM flags")
    suspend fun deleteAllFlags()
}
