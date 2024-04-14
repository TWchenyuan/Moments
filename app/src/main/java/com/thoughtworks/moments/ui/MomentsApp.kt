package com.thoughtworks.moments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.moments.ui.component.TodoScreen
import com.thoughtworks.moments.ui.component.moments.MomentsScreen
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationBar

@Composable
fun MomentsApp() {
  MomentsAppContent()
}

@Composable
fun MomentsAppContent(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  Column(modifier = modifier.fillMaxSize()) {
    MomentsNavHost(modifier = modifier.weight(1f), navController = navController)
    MomentsNavigationBar("Chat", {})
  }
}

@Composable
fun MomentsNavHost(modifier: Modifier, navController: NavHostController) {
  NavHost(modifier = modifier, navController = navController, startDestination = "Chat") {
    composable("Chat") {
      TodoScreen()
    }
    composable("Contact") {
      TodoScreen()
    }

    composable("Discover") {
      MomentsScreen()
    }

    composable("Me") {
      TodoScreen()
    }
  }
}
