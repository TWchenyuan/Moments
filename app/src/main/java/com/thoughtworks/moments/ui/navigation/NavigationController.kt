package com.thoughtworks.moments.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.thoughtworks.moments.ui.Screen

fun NavHostController.navigateTo(destination: Screen) {
  this.navigate(destination.route) {
    popUpTo(this@navigateTo.graph.findStartDestination().id) {
      saveState = true
    }
    launchSingleTop = true
    restoreState = true
  }
}
