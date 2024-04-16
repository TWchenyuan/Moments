package com.thoughtworks.moments.data.network.model

data class MomentData(
  val content: String?,
  val images: List<MomentImage>?,
  val sender: MomentSender?,
  val comments: List<MomentComment>?,
  val date: String?,
  val error: String?
) {
  data class MomentImage(val url: String)

  data class MomentSender(
    val username: String,
    val nick: String,
    val avatar: String
  )

  data class MomentComment(
    val content: String,
    val sender: MomentSender
  )
}
