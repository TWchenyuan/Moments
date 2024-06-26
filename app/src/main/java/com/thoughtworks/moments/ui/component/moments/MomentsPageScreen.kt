package com.thoughtworks.moments.ui.component.moments

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.thoughtworks.moments.R
import com.thoughtworks.moments.data.Account
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.ui.component.ImagePreviewScreen
import com.thoughtworks.moments.ui.theme.White100
import org.koin.androidx.compose.koinViewModel

@Composable
fun MomentsPageScreen(
  modifier: Modifier = Modifier,
  viewModel: MomentsPageViewModel = koinViewModel()
) {
  val momentsPageUiState by viewModel.momentsPageUiState.collectAsStateWithLifecycle()

  val enterImagePreview by viewModel.enterImagePreviewUiState.collectAsStateWithLifecycle()
  val imagePreviewUiState by viewModel.momentsPageImagePreviewUiState.collectAsStateWithLifecycle()
  val latestMoments = viewModel.latestMoments.collectAsLazyPagingItems()

  Box(modifier = modifier.fillMaxSize()) {
    MomentListContent(
      moments = latestMoments,
      currentAccount = momentsPageUiState.current,
      onImageClick = { selectedImageIndex, images ->
        viewModel.enterImagePreviewScreen(
          selectedImageIndex,
          images
        )
      }
    )
    AnimatedVisibility(visible = enterImagePreview) {
      ImagePreviewScreen(
        selectedImageIndex = imagePreviewUiState.currentImagePreviewIndex,
        images = imagePreviewUiState.currentImageList
      ) { viewModel.exitImagePreviewScreen() }
    }
  }
}

@Composable
fun MomentsPageImmersiveCover(
  modifier: Modifier = Modifier,
  cover: String,
  avatar: String,
  nick: String,
  @DrawableRes defaultCover: Int = R.drawable.pic_image_01
) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = Color.Transparent.toArgb()
      WindowCompat.setDecorFitsSystemWindows(window, false)
    }
    Box(
      modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 30.dp)
        .wrapContentHeight()
    ) {
      AsyncImage(
        model = cover,
        modifier = Modifier.fillMaxWidth(),
        placeholder = painterResource(id = defaultCover),
        contentDescription = stringResource(R.string.moments_page_image_immersive_image)
      )
      IconButton(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(top = 20.dp, end = 10.dp)
          .size(40.dp),
        onClick = { /*TODO*/ }
      ) {
        Icon(
          imageVector = ImageVector.vectorResource(R.drawable.filled_camera),
          contentDescription = stringResource(R.string.moments_page_publish_your_moments)
        )
      }
      Account(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .offset(0.dp, 30.dp),
        nickName = nick,
        avatar = avatar,
        defaultAvatar = R.drawable.default_avatar
      )
    }
  }
}

@Composable
fun Account(
  modifier: Modifier = Modifier,
  nickName: String,
  avatar: String,
  @DrawableRes defaultAvatar: Int = R.drawable.default_avatar
) {
  Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
    Text(
      text = nickName,
      modifier = Modifier.padding(bottom = 10.dp, end = 20.dp),
      color = White100,
      style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
    )
    Surface(modifier = Modifier.size(100.dp), shape = RoundedCornerShape(8.dp)) {
      AsyncImage(
        modifier = Modifier
          .size(100.dp)
          .clip(RoundedCornerShape(8.dp)),
        model = avatar,
        placeholder = painterResource(id = defaultAvatar),
        contentDescription = stringResource(R.string.moments_page_account_avatar)
      )
    }
  }
}

@Composable
fun MomentListContent(
  modifier: Modifier = Modifier,
  moments: LazyPagingItems<Moment>,
  currentAccount: Account,
  onImageClick: (selectedImageIndex: Int, images: List<String>) -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(White100)
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      item {
        MomentsPageImmersiveCover(
          defaultCover = R.drawable.pic_image_01,
          cover = currentAccount.cover,
          avatar = currentAccount.avatar,
          nick = currentAccount.nick
        )
      }
      items(count = moments.itemCount) { index ->
        moments[index]?.let {
          MomentsPageMomentItem(moment = it, onImageClick = onImageClick)
        }
      }
    }
  }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MomentsPageScreenPreview() {
  MomentsPageScreen()
}
