package com.thoughtworks.moments.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thoughtworks.moments.ui.component.MomentsDiscoverScreen
import com.thoughtworks.moments.ui.component.TodoScreen
import com.thoughtworks.moments.ui.component.moments.MomentsPageScreen

sealed class Screen(val route: String) {

  data object Chat : Screen("/chat")
  data object Contact : Screen("/contact")
  data object Discover : Screen("/discover")
  data object Me : Screen("/me")
  data object Moment : Screen("/moment")
}

fun NavGraphBuilder.buildGraph(navController: NavHostController) {
  composable(Screen.Chat.route) { TodoScreen() }
  composable(Screen.Contact.route) { TodoScreen() }
  composable(Screen.Discover.route) { MomentsDiscoverScreen(navController = navController) }
  composable(Screen.Me.route) { TodoScreen() }
  composable(Screen.Moment.route) { MomentsPageScreen() }
}
