package com.thoughtworks.moments.ui.component.moments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thoughtworks.moments.R
import com.thoughtworks.moments.data.Moment
import com.thoughtworks.moments.ui.theme.Dark10
import com.thoughtworks.moments.ui.theme.Link100

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
      Surface(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(8.dp)) {
        Image(
          painter = painterResource(id = R.drawable.default_avatar),
          contentDescription = "avatar",
          modifier = Modifier
            .size(60.dp)
            .padding(10.dp)
        )
      }

      Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        Text(
          text = moment.sender.nick,
          style = TextStyle.Default.copy(
            color = Link100,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
          ),
          modifier = Modifier.padding(vertical = 5.dp)
        )
        Text(text = moment.content, modifier = Modifier.padding(vertical = 5.dp))
        if (moment.images.isNotEmpty()) {
          MomentImageGallery(images = moment.images)
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
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = ImageVector.vectorResource(R.drawable.outlined_more),
              contentDescription = "more button"
            )
          }
        }
        HorizontalDivider(
          modifier = Modifier
            .height(1.dp)
            .background(Dark10)
        )
      }
    }
  }
}

@Composable
fun MomentImageGallery(modifier: Modifier = Modifier, images: List<String>) {
  // TODO
  Text(text = "Image place holder")
}
