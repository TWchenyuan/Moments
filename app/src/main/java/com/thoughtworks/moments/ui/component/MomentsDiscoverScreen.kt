package com.thoughtworks.moments.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thoughtworks.moments.R
import com.thoughtworks.moments.ui.theme.Dark100
import com.thoughtworks.moments.ui.theme.White100

@Composable
fun MomentsDiscoverScreen(modifier: Modifier = Modifier, navController: NavHostController) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .windowInsetsPadding(WindowInsets.statusBars)
  ) {
// top bar
    Text(text = "Discover", color = Dark100)
    DiscoverList(navigateTo = navController::navigate)
  }
}

@Composable
fun DiscoverList(modifier: Modifier = Modifier, navigateTo: (destination: String) -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    Column(
      modifier = modifier
        .fillMaxWidth()
        .background(White100)
    ) {
      DiscoverListItem(
        leftIcon = R.drawable.moment,
        title = "Moments",
        onClick = { navigateTo("MomentsPage") }
      )
      DiscoverListItem(leftIcon = R.drawable.moment, title = "Others")
    }
  }
}

@Composable
fun DiscoverListItem(
  modifier: Modifier = Modifier,
  @DrawableRes leftIcon: Int,
  title: String,
  @DrawableRes rightIcon: Int = R.drawable.outlined_arrow,
  onClick: () -> Unit = {},
  colorScheme: ColorScheme = MaterialTheme.colorScheme
) {
  Card(
    modifier = modifier
      .fillMaxWidth(),
    shape = RoundedCornerShape(0.dp),
    colors = CardDefaults.cardColors(
      containerColor = colorScheme.primary,
      contentColor = colorScheme.onPrimary
    ),
    onClick = onClick
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        modifier = Modifier
          .size(36.dp)
          .padding(8.dp),
        imageVector = ImageVector.vectorResource(leftIcon),
        contentDescription = "moments icon",
        tint = Color.Unspecified
      )
      Text(title)
      Spacer(modifier = Modifier.weight(1f))
      Icon(
        modifier = Modifier
          .size(26.dp)
          .padding(end = 8.dp),
        imageVector = ImageVector.vectorResource(rightIcon),
        contentDescription = "arrow icon"
      )
    }
  }
}
