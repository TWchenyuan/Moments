package com.thoughtworks.moments.data.network

import com.thoughtworks.moments.data.network.model.AccountData
import retrofit2.http.GET

interface AccountAPI {

  @GET("/moments-data/user.json")
  suspend fun getAccount(): AccountData
}
