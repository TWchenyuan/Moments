package com.thoughtworks.moments.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.moments.data.Moment

@Entity(
  tableName = "moment"
)
data class MomentEntity(
  @PrimaryKey
  val id: String,
  @ColumnInfo("content")
  val content: String,
  @ColumnInfo("images")
  val images: String,
  @ColumnInfo("sender")
  val sender: String,
  @ColumnInfo("comments")
  val comments: String,
  @ColumnInfo("created_at")
  val createdAt: Long,
  @ColumnInfo("likes")
  val likes: String
)

fun Moment.toEntity(): MomentEntity {
  val gson = Gson()
  return MomentEntity(
    id = this.id,
    content = this.content,
    images = gson.toJson(this.images, object : TypeToken<List<String>>() {}.type),
    sender = gson.toJson(this.sender, object : TypeToken<Moment.Sender>() {}.type),
    comments = gson.toJson(this.comments, object : TypeToken<List<Moment.Comment>>() {}.type),
    createdAt = this.createdAt,
    likes = gson.toJson(this.likes, object : TypeToken<List<String>>() {}.type)
  )
}
