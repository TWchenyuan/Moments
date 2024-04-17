package com.thoughtworks.moments.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.thoughtworks.moments.data.database.MomentDao
import com.thoughtworks.moments.data.database.MomentDatabase
import com.thoughtworks.moments.data.database.MomentEntity
import com.thoughtworks.moments.data.database.toEntity
import com.thoughtworks.moments.data.network.MomentAPI
import com.thoughtworks.moments.data.toMoment

@OptIn(ExperimentalPagingApi::class)
class MomentMediator(
  private val momentAPI: MomentAPI,
  private val momentDao: MomentDao,
  private val momentDatabase: MomentDatabase
) : RemoteMediator<Int, MomentEntity>() {
  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, MomentEntity>
  ): MediatorResult {
    return try {
      val moments = this.momentAPI.listMoments()
      momentDatabase.withTransaction {
        if (loadType == LoadType.REFRESH) momentDao.clearAll()
        momentDao.insertAllMoments(moments.mapNotNull { it.toMoment()?.toEntity() })
      }
      MediatorResult.Success(endOfPaginationReached = true)
    } catch (e: Exception) {
      MediatorResult.Error(e)
    }
  }
}
