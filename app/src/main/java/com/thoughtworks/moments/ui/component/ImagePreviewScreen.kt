package com.thoughtworks.moments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ImagePreviewScreen(
  modifier: Modifier = Modifier,
  selectedImageIndex: Int = 0,
  images: List<String>
) {
  Box(modifier = modifier.background(Color.Black)) {
  }
}
