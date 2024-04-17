package com.thoughtworks.moments.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.moments.data.database.MomentEntity
import com.thoughtworks.moments.data.network.model.MomentData
import java.time.Duration
import java.time.Instant
import java.util.UUID

data class Moment(
  val id: String,
  val content: String,
  val images: List<String>,
  val sender: Sender,
  val comments: List<Comment>,
  val createdAt: Long,
  val likes: List<String>
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
    comments = this.comments?.map { Moment.Comment(it.content, it.sender.nick) } ?: emptyList(),
    // TODO Real Date
    createdAt = Instant.now().minus(Duration.ofMinutes(10)).toEpochMilli(),
    likes = this.comments?.map { it.sender.nick } ?: emptyList()
  )
}
fun MomentEntity.toMoment(): Moment {
  val gson = Gson()

  return Moment(
    id = this.id,
    content = this.content,
    images = gson.fromJson(this.images, object : TypeToken<List<String>>() {}.type),
    sender = gson.fromJson(this.sender, object : TypeToken<Moment.Sender>() {}.type),
    comments = gson.fromJson(this.comments, object : TypeToken<List<Moment.Comment>>() {}.type),
    createdAt = this.createdAt,
    likes = gson.fromJson(this.likes, object : TypeToken<List<String>>() {}.type)
  )
}
