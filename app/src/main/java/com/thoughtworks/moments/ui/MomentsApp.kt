package com.thoughtworks.moments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MomentsApp() {
  Text("Hello world")
}

@Composable
fun MomentsNavigationWrapper() {
}

@Composable
fun MomentsAppContent(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  Column(modifier = modifier.fillMaxSize()) {
    MomentsNavHost(modifier = modifier, navController = navController)
  }
}

@Composable
fun MomentsNavHost(modifier: Modifier, navController: NavHostController) {
  NavHost(modifier = modifier, navController = navController, startDestination = "") {
    composable("Chat") {
    }
    composable("Contact") {
    }

    composable("Discover") {
    }

    composable("Me") {
    }
  }
}
