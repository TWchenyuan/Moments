package com.thoughtworks.moments.data

import com.thoughtworks.moments.data.network.model.AccountData

data class Account(
  val username: String,
  val nick: String,
  val cover: String,
  val avatar: String
)

fun AccountData.toAccount(): Account = Account(
  username = this.username,
  nick = this.nick,
  cover = this.profileImage,
  avatar = this.avatar
)
