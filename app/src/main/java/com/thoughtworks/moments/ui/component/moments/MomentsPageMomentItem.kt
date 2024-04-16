package com.thoughtworks.moments.ui.component.moments

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thoughtworks.moments.R
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.ui.theme.Dark10
import com.thoughtworks.moments.ui.theme.DividerColor
import com.thoughtworks.moments.ui.theme.Link100
import com.thoughtworks.moments.ui.theme.White100
import com.thoughtworks.moments.ui.theme.White97

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
  onLickClick: () -> Unit = {}
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
      Surface(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(8.dp)) {
        AsyncImage(
          model = moment.sender.avatar,
          placeholder = painterResource(id = R.drawable.default_avatar),
          contentDescription = "avatar",
          modifier = Modifier
            .size(60.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
        )
      }

      Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        Text(
          text = moment.sender.nick,
          style = TextStyle.Default.copy(
            color = Link100,
            fontWeight = FontWeight.Bold
          ),
          modifier = Modifier.padding(vertical = 5.dp)
        )
        Text(text = moment.content, modifier = Modifier.padding(vertical = 3.dp))
        if (moment.images.isNotEmpty()) {
          MomentImageGallery(images = moment.images.take(9))
        }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "11 hrs ago",
            style = TextStyle.Default.copy(color = Dark10, fontSize = 15.sp)
          )
          IconButton(
            modifier = Modifier
              .background(White97)
              .clip(RoundedCornerShape(3.dp))
              .width(20.dp)
              .height(15.dp),

            onClick = { /*TODO*/ }
          ) {
            Icon(
              imageVector = ImageVector.vectorResource(R.drawable.outlined_more),
              contentDescription = "more button",
              tint = Link100
            )
          }
        }
        // TODO LIKE
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
    }
  }
}

@Composable
fun MomentImageGallery(modifier: Modifier = Modifier, images: List<String>) {
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
      items(images.size) {
        AsyncImage(
          model = images[it],
          contentDescription = "image in gallery",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .height(spec.imageHeight)
            .width(spec.imageWidth)
            .clip(RoundedCornerShape(4.dp))
        )
      }
    }
  }
}
