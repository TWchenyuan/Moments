package com.thoughtworks.moments.ui.component.moments

import androidx.lifecycle.ViewModel
import com.thoughtworks.moments.data.Account
import com.thoughtworks.moments.data.Moment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MomentsPageUiState(
  val current: Account = Account("username", "nick", "", ""),
  val latestMoments: List<Moment> = emptyList()
)

class MomentsPageViewModel : ViewModel() {

  private val _momentsPageUiState: MutableStateFlow<MomentsPageUiState> = MutableStateFlow(
    MomentsPageUiState(
      latestMoments = listOf(
        Moment(
          "moment_1",
          "first",
          images = emptyList(),
          Moment.Sender("user_1", "avatar", "nick_name"),
          emptyList()
        )
      )
    )
  )
  val momentsPageUiState: StateFlow<MomentsPageUiState> get() = _momentsPageUiState
}
