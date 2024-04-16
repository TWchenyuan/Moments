package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.network.MomentAPI
import com.thoughtworks.moments.data.toMoment

class MomentRepositoryImpl(private val momentApi: MomentAPI) : MomentRepository {
  override suspend fun latestMoments(): List<Moment> =
    this.momentApi.listMoments().mapNotNull { it.toMoment() }
}
