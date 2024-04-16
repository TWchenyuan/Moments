package com.thoughtworks.moments.data.network.model

import com.google.gson.annotations.SerializedName

data class AccountData(
  @SerializedName("profile-image")
  val profileImage: String,
  val avatar: String,
  val nick: String,
  val username: String
)
