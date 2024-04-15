package com.thoughtworks.moments.ui.component.moments

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import com.thoughtworks.moments.R

@Composable
fun MomentsPageScreen(
  modifier: Modifier = Modifier,
  viewModel: MomentsPageViewModel = MomentsPageViewModel()
) {
  Surface(modifier = modifier.fillMaxSize()) {
    MomentsPageImmersiveHeader(imageId = R.drawable.pic_image_01)
  }
}

@Composable
fun MomentsPageImmersiveHeader(modifier: Modifier = Modifier, @DrawableRes imageId: Int) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = Color.Transparent.toArgb()
      WindowCompat.setDecorFitsSystemWindows(window, false)
    }
    Box(modifier = modifier.fillMaxWidth()) {
      Image(painter = painterResource(id = imageId), contentDescription = "immersive image")
    }
  }
}
