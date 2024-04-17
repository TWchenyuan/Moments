package com.thoughtworks.moments.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MomentDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMoment(momentEntity: MomentEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllMoments(moments: List<MomentEntity>)

  @Query("SELECT * FROM moment")
  fun getPagedMoments(): PagingSource<Int, MomentEntity>

  @Query("DELETE FROM moment")
  suspend fun clearAll()
}
