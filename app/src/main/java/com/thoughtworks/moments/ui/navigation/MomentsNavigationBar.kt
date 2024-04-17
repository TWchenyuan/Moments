package com.thoughtworks.moments.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.moments.R
import com.thoughtworks.moments.ui.Screen
import com.thoughtworks.moments.ui.theme.Brand100
import com.thoughtworks.moments.ui.theme.Brand120
import com.thoughtworks.moments.ui.theme.White100

enum class NavigationItem(
  val route: Screen,
  @DrawableRes val unSelectedIconId: Int,
  @DrawableRes val selectedIconId: Int,
  @StringRes val iconTextId: Int,
  @StringRes val textLabelId: Int
) {

  CHAT(
    route = Screen.Chat,
    unSelectedIconId = R.drawable.outlined_chats,
    selectedIconId = R.drawable.filled_chats,
    iconTextId = R.string.tab_chat_icon_desc,
    textLabelId = R.string.tab_chat
  ),

  CONTACT(
    route = Screen.Contact,
    unSelectedIconId = R.drawable.outlined_contacts,
    selectedIconId = R.drawable.filled_contacts,
    iconTextId = R.string.tab_contact_icon_desc,
    textLabelId = R.string.tab_contact
  ),

  DISCOVER(
    route = Screen.Discover,
    unSelectedIconId = R.drawable.outlined_discover,
    selectedIconId = R.drawable.filled_discover,
    iconTextId = R.string.tab_discover_icon_desc,
    textLabelId = R.string.tab_discover
  ),

  ME(
    route = Screen.Me,
    unSelectedIconId = R.drawable.outlined_me,
    selectedIconId = R.drawable.filled_me,
    iconTextId = R.string.tab_me_icon_dsc,
    textLabelId = R.string.tab_me
  )
}

val NavigationItems =
  listOf(NavigationItem.CHAT, NavigationItem.CONTACT, NavigationItem.DISCOVER, NavigationItem.ME)

@Composable
fun MomentsNavigationBar(
  selectedDestination: String,
  navigateToTopLevelDestination: (Screen) -> Unit
) {
  NavigationBar(
    modifier = Modifier
      .fillMaxWidth(),
    containerColor = White100
  ) {
    NavigationItems.forEach {
      val selected = selectedDestination == it.route.route
      NavigationBarItem(
        selected = selected,
        onClick = { navigateToTopLevelDestination(it.route) },
        icon = {
          Icon(
            imageVector = ImageVector.vectorResource(
              if (selected) it.selectedIconId else it.unSelectedIconId
            ),
            contentDescription = stringResource(id = it.iconTextId)
          )
        },
        label = { Text(text = stringResource(id = it.textLabelId)) },
        colors = NavigationBarItemDefaults.colors(
          selectedTextColor = Brand100,
          selectedIconColor = Brand120,
          indicatorColor = Color.Transparent
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
