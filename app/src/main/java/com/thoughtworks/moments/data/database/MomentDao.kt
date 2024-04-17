package com.thoughtworks.moments.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MomentDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMoment(momentEntity: MomentEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllMoments(moments: List<MomentEntity>)
}
