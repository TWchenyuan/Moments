package com.thoughtworks.moments.ui.component.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.moments.ui.theme.Brand100
import com.thoughtworks.moments.ui.theme.Brand120
import com.thoughtworks.moments.ui.theme.White100

@Composable
fun MomentsNavigationBar(
  selectedDestination: String,
  navigateToTopLevelDestination: (MomentsNavigationItem) -> Unit
) {
  NavigationBar(
    modifier = Modifier
      .fillMaxWidth(),
    containerColor = White100
  ) {
    MOMENTS_NAVIGATION_ITEM_LIST.forEach {
      NavigationBarItem(
        selected = selectedDestination == it.route,
        onClick = { navigateToTopLevelDestination(it) },
        icon = {
          Icon(
            imageVector = ImageVector.vectorResource(it.unSelectedIconId),
            contentDescription = stringResource(id = it.iconTextId)
          )
        },
        label = { Text(text = stringResource(id = it.textLabelId)) },
        colors = NavigationBarItemDefaults.colors(
          selectedTextColor = Brand100,
          selectedIconColor = Brand120
        )
      )
    }
  }
}

@Preview
@Composable
fun MomentsNavigationBarPreview() {
  MomentsNavigationBar("Chat", {})
}
