package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.network.MomentAPI

class MomentRepositoryImpl(momentApi: MomentAPI) : MomentRepository {
  override suspend fun latestMoments(): List<Moment> {
    TODO("Not yet implemented")
  }
}
