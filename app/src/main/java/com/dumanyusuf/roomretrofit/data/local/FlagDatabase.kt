package com.dumanyusuf.roomretrofit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlagEntity::class],
    version = 1
)
abstract class FlagDatabase : RoomDatabase() {
    abstract val dao: FlagDao
}
