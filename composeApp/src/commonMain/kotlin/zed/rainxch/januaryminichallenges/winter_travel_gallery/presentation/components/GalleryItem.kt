package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.januaryminichallenges.core.presentation.WinterTravelGalleryColors
import zed.rainxch.januaryminichallenges.core.presentation.plusJakartaSansFont
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.model.Destination

@Composable
fun GalleryItem(
    destination: Destination,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(destination.cover),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xff061628).copy(alpha = 0f),
                            Color(0xff061628),
                        )
                    )
                )
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = destination.title,
                fontFamily = plusJakartaSansFont(),
                fontWeight = FontWeight.Medium,
                color = WinterTravelGalleryColors.textCard,
                fontSize = 18.sp
            )
        }
    }
}