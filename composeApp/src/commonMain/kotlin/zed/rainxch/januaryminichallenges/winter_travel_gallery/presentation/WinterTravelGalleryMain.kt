package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.januaryminichallenges.core.presentation.WinterTravelGalleryColors
import zed.rainxch.januaryminichallenges.core.presentation.plusJakartaSansFont
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.components.GalleryItem
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.model.Destination

@Composable
fun WinterTravelGalleryMainScreen(
    onNavigateToGallery : (Destination) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WinterTravelGalleryColors.bgMain)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                vertical = 32.dp,
                horizontal = 16.dp
            )
    ) {
        Text(
            text = "Winter Travel Gallery",
            fontFamily = plusJakartaSansFont(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            color = WinterTravelGalleryColors.textPrimary
        )

        Spacer(Modifier.height(20.dp))

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(Destination.entries) { destination ->
                GalleryItem(
                    destination = destination,
                    onClick = {
                        onNavigateToGallery(destination)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WinterTravelGalleryMainScreen(
        onNavigateToGallery = { }
    )
}