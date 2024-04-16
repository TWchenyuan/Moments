package com.thoughtworks.moments.data.network

import com.thoughtworks.moments.data.network.model.MomentData
import retrofit2.http.GET

interface MomentAPI {

  @GET("/tweets.json")
  suspend fun listMoments(): List<MomentData>
}
