package com.thoughtworks.moments.ui.component.moments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.moments.data.Account
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.data.repository.AccountRepository
import com.thoughtworks.moments.data.repository.MomentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MomentsPageUiState(
  val current: Account = Account("", "", "", ""),
  val latestMoments: List<Moment> = emptyList(),
  val loadError: String? = null
)

class MomentsPageViewModel(
  private val accounts: AccountRepository,
  private val moments: MomentRepository
) : ViewModel() {

  private val _momentsPageUiState: MutableStateFlow<MomentsPageUiState> = MutableStateFlow(
    MomentsPageUiState()
  )
  val momentsPageUiState: StateFlow<MomentsPageUiState> get() = _momentsPageUiState

  init {
    loadMoments()
  }

  private fun loadMoments() {
    viewModelScope.launch {
      try {
        val moments = moments.latestMoments()
        _momentsPageUiState.value = _momentsPageUiState.value.copy(
          latestMoments = moments
        )
      } catch (e: Exception) {
        _momentsPageUiState.value = _momentsPageUiState.value.copy(
          loadError = e.message ?: "Loading Error"
        )
      }
    }
  }
}
