package com.thoughtworks.moments.ui.component.moments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.thoughtworks.moments.R
import com.thoughtworks.moments.data.Moment

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
    Row(modifier = Modifier.fillMaxWidth()) {
      Image(
        painter = painterResource(id = R.drawable.default_avatar),
        contentDescription = "avatar"
      )

      Column {
        Text(text = moment.sender.nick)
        Text(text = moment.content)
        if (moment.images.isNotEmpty()) {
          MomentImageGallery(images = moment.images)
        }
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        Text(text = "11 hrs ago")
        IconButton(onClick = { /*TODO*/ }) {
          Icon(
            imageVector = ImageVector.vectorResource(R.drawable.outlined_more),
            contentDescription = "more button"
          )
        }
      }
    }
    Spacer(
      modifier = Modifier
        .height(2.dp)
        .background(Color.Black)
    )
  }
}

@Composable
fun MomentImageGallery(modifier: Modifier = Modifier, images: List<String>) {
  // TODO
  Text(text = "Image place holder")
}
