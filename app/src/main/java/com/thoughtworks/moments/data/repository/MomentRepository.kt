package com.thoughtworks.moments.data.repository

import androidx.paging.PagingData
import com.thoughtworks.moments.data.Moment
import kotlinx.coroutines.flow.Flow

interface MomentRepository {
  suspend fun latestMoments(): List<Moment>

  fun getPagingMoments(pageSize: Int): Flow<PagingData<Moment>>
}
