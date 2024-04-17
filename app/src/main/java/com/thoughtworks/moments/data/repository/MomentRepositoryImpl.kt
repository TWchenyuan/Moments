package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.database.MomentDao
import com.thoughtworks.moments.data.database.toEntity
import com.thoughtworks.moments.data.network.MomentAPI
import com.thoughtworks.moments.data.toMoment

class MomentRepositoryImpl(
  private val momentApi: MomentAPI,
  private val momentDao: MomentDao
) : MomentRepository {
  override suspend fun latestMoments(): List<Moment> {
    val moments = this.momentApi.listMoments().mapNotNull { it.toMoment() }
    this.momentDao.insertAllMoments(moments.map { it.toEntity() })
    return moments
  }
}
