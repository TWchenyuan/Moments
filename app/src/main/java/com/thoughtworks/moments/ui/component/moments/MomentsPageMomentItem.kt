package com.thoughtworks.moments.ui.component.moments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thoughtworks.moments.R
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.ui.theme.Dark10
import com.thoughtworks.moments.ui.theme.Dark5
import com.thoughtworks.moments.ui.theme.DividerColor
import com.thoughtworks.moments.ui.theme.Link100
import com.thoughtworks.moments.ui.theme.White100
import com.thoughtworks.moments.ui.theme.White97
import com.thoughtworks.moments.ui.util.maskTime

private val SpacedInImagesDp = 3.dp
private val MinImageSizeDp = 90.dp
private val FixedMaxImageHeightOrWidthDp = MinImageSizeDp * 3 + SpacedInImagesDp * 2
private val TwoLineHeightDp = MinImageSizeDp * 2 + SpacedInImagesDp

data class GallerySpec(
  val gridHeight: Dp,
  val gridCellsSize: Int = 3,
  val imageHeight: Dp = 90.dp,
  val imageWidth: Dp = 90.dp
)

@Composable
fun MomentsPageMomentItem(
  modifier: Modifier = Modifier,
  moment: Moment,
  onImageClick: (selectedImageIndex: Int, images: List<String>) -> Unit
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(10.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(White100)
    ) {
      FriendsAvatar(moment.sender.avatar)
      Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        FriendsNick(moment.sender.nick)
        FriendsMomentContent(moment.content)
        if (moment.images.isNotEmpty()) {
          MomentImageGallery(images = moment.images.take(9), onImageClick = onImageClick)
        }
        TimeAndMoreButton(moment.createdAt)
        if (moment.likes.isNotEmpty() || moment.comments.isNotEmpty()) {
          LikeAndComments(likes = moment.likes, comments = moment.comments)
        }
        MomentDivider()
      }
    }
  }
}

@Composable
fun MomentDivider() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 10.dp)
  ) {
    HorizontalDivider(
      modifier = Modifier
        .height(1.dp)
        .background(DividerColor)
    )
  }
}

@Composable
fun LikeAndComments(
  modifier: Modifier = Modifier,
  likes: List<String>,
  comments: List<Moment.Comment>
) {
  val likeListString = likes.joinToString(", ")
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(White97)
      .padding(10.dp)
  ) {
    if (likes.isNotEmpty()) {
      Text(
        text = buildAnnotatedString {
          appendInlineContent("inlineContent", "[like]")
          append(likeListString)
        },
        style = MaterialTheme.typography.labelLarge,
        inlineContent = mapOf(
          Pair(
            "inlineContent",
            InlineTextContent(
              placeholder = Placeholder(
                width = 16.sp,
                height = 16.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
              )
            ) {
              Icon(
                modifier = Modifier.fillMaxWidth(),
                imageVector = ImageVector.vectorResource(R.drawable.outlined_like),
                contentDescription = "like icon"
              )
            }
          )
        )
      )
    }

    comments.forEach {
      Text(
        modifier = Modifier
          .fillMaxWidth(),
        text = AnnotatedString.Builder("").apply {
          pushStyle(
            SpanStyle(
              color = Link100
            )
          )
          append(it.senderNick)
          pop()
          append(" :${it.content}")
        }.toAnnotatedString()
      )
    }
  }
}

@Composable
fun TimeAndMoreButton(createdAt: Long) {
  val expanded = remember { mutableStateOf(false) }
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(40.dp)
  ) {
    Text(
      modifier = Modifier.align(Alignment.CenterStart),
      text = createdAt.maskTime(),
      style = TextStyle.Default.copy(color = Dark10, fontSize = 15.sp)
    )

    Row(
      modifier = Modifier.align(Alignment.CenterEnd),
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.CenterVertically
    ) {
      AnimatedVisibility(visible = expanded.value) {
        Surface(
          modifier = Modifier
            .wrapContentWidth()
            .padding(5.dp),
          shape = RoundedCornerShape(2.dp),
          color = Dark5,
          contentColor = White100
        ) {
          Row(
            modifier = Modifier
              .wrapContentWidth().padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
          ) {
            Icon(
              modifier = Modifier.size(15.dp),
              imageVector = ImageVector.vectorResource(R.drawable.outlined_like),
              contentDescription = stringResource(R.string.ic_like_action_icon)
            )
            Text(
              text = stringResource(R.string.like_menu_item),
              style = TextStyle.Default.copy(fontSize = 15.sp)
            )
            VerticalDivider(
              modifier = Modifier
                .width(1.dp)
                .padding(vertical = 2.dp)
            )
            Icon(
              modifier = Modifier.size(15.dp),
              imageVector = ImageVector.vectorResource(R.drawable.outlined_comment),
              contentDescription = stringResource(R.string.ic_comment_action_icon)
            )
            Text(
              text = stringResource(R.string.comment_menu_item),
              style = TextStyle.Default.copy(fontSize = 15.sp)
            )
          }
        }
      }
      IconButton(
        modifier = Modifier
          .background(White97)
          .clip(RoundedCornerShape(3.dp))
          .width(20.dp)
          .height(15.dp),

        onClick = { expanded.value = !expanded.value }
      ) {
        Icon(
          imageVector = ImageVector.vectorResource(R.drawable.outlined_more),
          contentDescription = stringResource(R.string.ic_more_button),
          tint = Link100
        )
      }
    }
//    Row(
//      modifier = Modifier
//        .fillMaxWidth()
//        .padding(vertical = 10.dp)
//        .wrapContentHeight(),
//      verticalAlignment = Alignment.CenterVertically,
//      horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//      Text(
//        text = createdAt.maskTime(),
//        style = TextStyle.Default.copy(color = Dark10, fontSize = 15.sp)
//      )
//      IconButton(
//        modifier = Modifier
//          .background(White97)
//          .clip(RoundedCornerShape(3.dp))
//          .width(20.dp)
//          .height(15.dp),
//
//        onClick = { expanded.value = true }
//      ) {
//        Icon(
//          imageVector = ImageVector.vectorResource(R.drawable.outlined_more),
//          contentDescription = "more button",
//          tint = Link100
//        )
//      }
//    }
  }
}

@Composable
fun FriendsMomentContent(content: String) {
  // TODO show more
  Text(text = content, modifier = Modifier.padding(vertical = 3.dp))
}

@Composable
fun FriendsNick(nick: String) {
  Text(
    text = nick,
    style = MaterialTheme.typography.labelLarge,
    modifier = Modifier.padding(vertical = 5.dp)
  )
}

@Composable
fun FriendsAvatar(avatar: String) {
  Surface(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(8.dp)) {
    AsyncImage(
      model = avatar,
      placeholder = painterResource(id = R.drawable.default_avatar),
      contentDescription = "avatar",
      modifier = Modifier
        .size(60.dp)
        .padding(10.dp)
        .clip(RoundedCornerShape(8.dp))
    )
  }
}

@Composable
fun MomentImageGallery(
  modifier: Modifier = Modifier,
  images: List<String>,
  onImageClick: (selectedImageIndex: Int, images: List<String>) -> Unit
) {
  val spec = when (images.size) {
    0 -> GallerySpec(gridHeight = 0.dp, 0, 0.dp, 0.dp)
    1 -> GallerySpec(
      gridHeight = FixedMaxImageHeightOrWidthDp,
      gridCellsSize = images.size,
      imageHeight = FixedMaxImageHeightOrWidthDp,
      imageWidth = FixedMaxImageHeightOrWidthDp
    )

    2, 3 -> GallerySpec(gridHeight = MinImageSizeDp, gridCellsSize = images.size)
    4, 5, 6 -> GallerySpec(gridHeight = TwoLineHeightDp)
    else -> GallerySpec(gridHeight = FixedMaxImageHeightOrWidthDp)
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(vertical = 10.dp)
  ) {
    LazyVerticalGrid(
      modifier = modifier
        .fillMaxWidth()
        .height(spec.gridHeight),
      verticalArrangement = Arrangement.spacedBy(SpacedInImagesDp),
      horizontalArrangement = Arrangement.spacedBy(SpacedInImagesDp),
      columns = GridCells.Fixed(spec.gridCellsSize),
      userScrollEnabled = false
    ) {
      itemsIndexed(images) { index, item ->
        AsyncImage(
          model = item,
          contentDescription = "image in gallery",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .height(spec.imageHeight)
            .width(spec.imageWidth)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onImageClick(index, images) }
        )
      }
    }
  }
}
