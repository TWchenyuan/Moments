package com.thoughtworks.moments.ui.component.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MomentsNavigationBar(
  selectedDestination: String,
  navigateToTopLevelDestination: (String) -> Unit
) {
  NavigationBar(modifier = Modifier.fillMaxWidth()) {
    listOf("Chat", "Contact", "Discover", "Me").forEach {
      NavigationBarItem(
        selected = selectedDestination == it,
        onClick = { navigateToTopLevelDestination(it) },
        icon = { /*TODO*/ },
        label = { Text(it) }

      )
    }
  }
}

@Preview
@Composable
fun MomentsNavigationBarPreview() {
  MomentsNavigationBar("Home", {})
}
