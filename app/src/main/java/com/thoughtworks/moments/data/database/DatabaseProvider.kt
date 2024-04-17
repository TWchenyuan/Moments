package com.thoughtworks.moments.data.database

import android.content.Context
import androidx.room.Room

const val DATABASE_NAME = "Moment_Database"
fun provideDatabase(context: Context): MomentDatabase {
  return Room.databaseBuilder(context, MomentDatabase::class.java, DATABASE_NAME).build()
}

fun provideMomentDao(database: MomentDatabase): MomentDao = database.momentDao()
