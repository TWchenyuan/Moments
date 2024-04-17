package com.thoughtworks.moments.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.database.MomentDao
import com.thoughtworks.moments.data.paging.MomentMediator
import com.thoughtworks.moments.data.toMoment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MomentRepositoryImpl(
  private val momentDao: MomentDao,
  private val momentMediator: MomentMediator
) : MomentRepository {
  @OptIn(ExperimentalPagingApi::class)
  override fun getPagingMoments(pageSize: Int): Flow<PagingData<Moment>> {
    return Pager(
      config = PagingConfig(pageSize = pageSize),
      remoteMediator = momentMediator,
      pagingSourceFactory = { momentDao.getPagedMoments() }
    ).flow.map { it.map { it.toMoment() } }
  }
}
