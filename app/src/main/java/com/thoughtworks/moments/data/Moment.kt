package com.thoughtworks.moments.data

import com.thoughtworks.moments.data.network.model.MomentData
import java.util.UUID

data class Moment(
  val id: String,
  val content: String,
  val images: List<String>,
  val sender: Sender,
  val comments: List<Comment>
) {
  data class Sender(
    val userId: String,
    val avatar: String,
    val nick: String
  )

  data class Comment(
    val content: String,
    val senderNick: String
  )
}

fun MomentData.toMoment(): Moment? {
  if (this.content == null || this.sender == null || this.error != null) return null

  return Moment(
    id = UUID.randomUUID().toString().replace("-", ""),
    content = this.content,
    images = this.images?.map { it.url } ?: emptyList(),
    sender = Moment.Sender(
      userId = this.sender.username,
      nick = this.sender.nick,
      avatar = this.sender.avatar
    ),
    comments = this.comments?.map { Moment.Comment(it.content, it.sender.nick) } ?: emptyList()
  )
}
