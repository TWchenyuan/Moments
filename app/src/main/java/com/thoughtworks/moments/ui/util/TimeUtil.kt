package com.thoughtworks.moments.ui.util

import java.time.Instant

fun Long.maskTime(): String {
  val now = System.currentTimeMillis()
  val diff = (now - this) / 1000

  return when {
    diff < 60 -> "${diff / 1000} seconds ago"
    diff / 60 < 60 -> "${diff / 60} minutes ago"
    diff / (60 * 60) < 60 -> "${diff / (60 * 60)} hours go"
    else -> Instant.ofEpochMilli(this).toString()
  }
}
