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

data class MomentsPageImagePreviewUiState(
  val currentImagePreviewIndex: Int = 0,
  val currentImageList: List<String> = emptyList()
)

class MomentsPageViewModel(
  private val accounts: AccountRepository,
  private val moments: MomentRepository
) : ViewModel() {

  private val _momentsPageUiState: MutableStateFlow<MomentsPageUiState> = MutableStateFlow(
    MomentsPageUiState()
  )
  val momentsPageUiState: StateFlow<MomentsPageUiState> get() = _momentsPageUiState

  private val _momentsPageImagePreviewUiState: MutableStateFlow<MomentsPageImagePreviewUiState> =
    MutableStateFlow(
      MomentsPageImagePreviewUiState()
    )

  val momentsPageImagePreviewUiState: StateFlow<MomentsPageImagePreviewUiState>
    get() =
      _momentsPageImagePreviewUiState

  private val _enterImagePreviewUiState: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val enterImagePreviewUiState: StateFlow<Boolean> get() = _enterImagePreviewUiState

  init {
    loadMoments()
  }

  private fun loadMoments() {
    viewModelScope.launch {
      try {
        val current = accounts.currentAccount()
        val moments = moments.latestMoments()
        _momentsPageUiState.value = _momentsPageUiState.value.copy(
          latestMoments = moments,
          current = current
        )
      } catch (e: Exception) {
        _momentsPageUiState.value = _momentsPageUiState.value.copy(
          loadError = e.message ?: "Loading Error"
        )
      }
    }
  }

  fun enterImagePreviewScreen(selectedImageIndex: Int, images: List<String>) {
    this._enterImagePreviewUiState.value = true
    this._momentsPageImagePreviewUiState.value = _momentsPageImagePreviewUiState.value.copy(
      currentImageList = images,
      currentImagePreviewIndex = selectedImageIndex
    )
  }

  fun exitImagePreviewScreen() {
    this._enterImagePreviewUiState.value = false
  }
}
