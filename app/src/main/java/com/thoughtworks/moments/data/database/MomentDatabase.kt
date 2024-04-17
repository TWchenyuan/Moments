package com.thoughtworks.moments.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MomentEntity::class], version = 1)
abstract class MomentDatabase : RoomDatabase() {
  abstract fun momentDao(): MomentDao
}
