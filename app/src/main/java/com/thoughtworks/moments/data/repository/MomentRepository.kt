package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Moment

interface MomentRepository {
  suspend fun latestMoments(): List<Moment>
}
