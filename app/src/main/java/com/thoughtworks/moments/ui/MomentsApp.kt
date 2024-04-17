package com.thoughtworks.moments.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.moments.ui.navigation.MOMENTS_NAVIGATION_ITEM_LIST
import com.thoughtworks.moments.ui.navigation.MomentsNavigationBar
import com.thoughtworks.moments.ui.navigation.MomentsNavigationItem.Chat
import com.thoughtworks.moments.ui.navigation.navigateTo

@Composable
fun MomentsApp() {
  MomentsAppContent()
}

@Composable
fun MomentsAppContent(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val selectedDestination = navBackStackEntry?.destination?.route ?: Screen.Chat.route

  Scaffold(
    modifier = modifier.fillMaxSize(),
    bottomBar = {
      AnimatedVisibility(
        visible = MOMENTS_NAVIGATION_ITEM_LIST.map { it.route.route }
          .contains(selectedDestination)
      ) {
        MomentsNavigationBar(
          selectedDestination = selectedDestination,
          navController::navigateTo
        )
      }
    }
  ) {
    MomentsNavHost(
      navController = navController,
      paddingValues = it
    )
  }
}

@Composable
fun MomentsNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  paddingValues: PaddingValues
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Screen.Chat.route
  ) {
    buildGraph(navController)
  }
}
