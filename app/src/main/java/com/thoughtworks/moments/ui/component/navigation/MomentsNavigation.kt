package com.thoughtworks.moments.ui.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.thoughtworks.moments.R

sealed class MomentsNavigationItem(
  val route: String,
  val icon: ImageVector,
  val iconTextId: Int,
  val textLabelId: Int
) {
  data object Chat : MomentsNavigationItem(
    route = "Chat",
    icon = Icons.Default.Home,
    iconTextId = R.string.tab_chat_icon_desc,
    textLabelId = R.string.tab_chat
  )

  data object Contact : MomentsNavigationItem(
    route = "Contact",
    icon = Icons.Default.Call,
    iconTextId = R.string.tab_contact_icon_desc,
    textLabelId = R.string.tab_contact
  )

  data object Discover : MomentsNavigationItem(
    route = "Discover",
    icon = Icons.Default.Share,
    iconTextId = R.string.tab_discover_icon_desc,
    textLabelId = R.string.tab_discover
  )

  data object Me : MomentsNavigationItem(
    route = "Me",
    icon = Icons.Default.AccountBox,
    iconTextId = R.string.tab_me_icon_dsc,
    textLabelId = R.string.tab_me
  )
}

val MOMENTS_NAVIGATION_ITEM_LIST = listOf(
  MomentsNavigationItem.Chat,
  MomentsNavigationItem.Contact,
  MomentsNavigationItem.Discover,
  MomentsNavigationItem.Me
)

fun NavHostController.navigateTo(destination: MomentsNavigationItem) {
  this.navigate(destination.route) {
    popUpTo(this@navigateTo.graph.findStartDestination().id) {
      saveState = true
    }
    launchSingleTop = true
    restoreState = true
  }
}
