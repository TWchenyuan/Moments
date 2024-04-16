package com.thoughtworks.moments.data.network

import com.thoughtworks.moments.data.Account
import retrofit2.http.GET

interface AccountAPI {

  @GET("/user.json")
  suspend fun getAccount(): Account
}
