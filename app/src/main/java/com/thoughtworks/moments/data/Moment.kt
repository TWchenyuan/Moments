package com.thoughtworks.moments.data

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
    val sender: Sender
  )
}
