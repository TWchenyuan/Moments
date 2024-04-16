package com.thoughtworks.moments.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.thoughtworks.moments.ui.theme.Dark100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePreviewScreen(
  modifier: Modifier = Modifier,
  selectedImageIndex: Int = 0,
  images: List<String>,
  onClick: () -> Unit
) {
  val pageState = rememberPagerState(initialPage = selectedImageIndex, pageCount = { images.size })
  Box(
    modifier = modifier
      .background(Color.Black)
      .fillMaxSize()
      .background(Dark100.copy(0.5f))
      .clickable { onClick() }
  ) {
    HorizontalPager(
      modifier = Modifier.fillMaxSize(),
      state = pageState
    ) {
      AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Fit,
        model = images[it],
        contentDescription = "image preview screen image"
      )
    }
  }
}
