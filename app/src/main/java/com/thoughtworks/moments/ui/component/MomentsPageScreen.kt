package com.thoughtworks.moments.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MomentsPageScreen(modifier: Modifier = Modifier) {
  Surface(modifier = modifier.fillMaxSize()) {
    Text(text = "Moments page")
  }
}
