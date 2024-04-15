package com.thoughtworks.moments.ui.component.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.thoughtworks.moments.R

sealed class MomentsNavigationItem(
  val route: String,
  val unSelectedIconId: Int,
  val selectedIconId: Int,
  val iconTextId: Int,
  val textLabelId: Int
) {
  data object Chat : MomentsNavigationItem(
    route = "Chat",
    unSelectedIconId = R.drawable.outlined_chats,
    selectedIconId = R.drawable.filled_chats,
    iconTextId = R.string.tab_chat_icon_desc,
    textLabelId = R.string.tab_chat
  )

  data object Contact : MomentsNavigationItem(
    route = "Contact",
    unSelectedIconId = R.drawable.outlined_contacts,
    selectedIconId = R.drawable.filled_contacts,
    iconTextId = R.string.tab_contact_icon_desc,
    textLabelId = R.string.tab_contact
  )

  data object Discover : MomentsNavigationItem(
    route = "Discover",
    unSelectedIconId = R.drawable.outlined_discover,
    selectedIconId = R.drawable.filled_discover,
    iconTextId = R.string.tab_discover_icon_desc,
    textLabelId = R.string.tab_discover
  )

  data object Me : MomentsNavigationItem(
    route = "Me",
    unSelectedIconId = R.drawable.outlined_me,
    selectedIconId = R.drawable.filled_me,
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
