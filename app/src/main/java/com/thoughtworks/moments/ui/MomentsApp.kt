package com.thoughtworks.moments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.moments.ui.component.TodoScreen
import com.thoughtworks.moments.ui.component.moments.MomentsScreen
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationBar
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationItem.Chat
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationItem.Contact
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationItem.Discover
import com.thoughtworks.moments.ui.component.navigation.MomentsNavigationItem.Me
import com.thoughtworks.moments.ui.component.navigation.navigateTo

@Composable
fun MomentsApp() {
  MomentsAppContent()
}

@Composable
fun MomentsAppContent(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val selectedDestination = navBackStackEntry?.destination?.route ?: Chat.route

  Column(modifier = modifier.fillMaxSize()) {
    MomentsNavHost(modifier = modifier.weight(1f), navController = navController)
    MomentsNavigationBar(selectedDestination, navController::navigateTo)
  }
}

@Composable
fun MomentsNavHost(modifier: Modifier, navController: NavHostController) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Chat.route
  ) {
    composable(Chat.route) {
      TodoScreen()
    }
    composable(Contact.route) {
      TodoScreen()
    }
    composable(Discover.route) {
      MomentsScreen()
    }
    composable(Me.route) {
      TodoScreen()
    }
  }
}
