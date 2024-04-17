package com.thoughtworks.moments.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.database.MomentDao
import com.thoughtworks.moments.data.database.toEntity
import com.thoughtworks.moments.data.network.MomentAPI
import com.thoughtworks.moments.data.paging.MomentSource
import com.thoughtworks.moments.data.toMoment
import kotlinx.coroutines.flow.Flow

class MomentRepositoryImpl(
  private val momentApi: MomentAPI,
  private val momentDao: MomentDao,
  private val momentsSource: MomentSource
) : MomentRepository {
  override suspend fun latestMoments(): List<Moment> {
    val moments = this.momentApi.listMoments().mapNotNull { it.toMoment() }
    this.momentDao.insertAllMoments(moments.map { it.toEntity() })
    return moments
  }

  override fun getPagingMoments(pageSize: Int): Flow<PagingData<Moment>> {
    return Pager(
      config = PagingConfig(pageSize = pageSize),
      pagingSourceFactory = { momentsSource }
    ).flow
  }
}
