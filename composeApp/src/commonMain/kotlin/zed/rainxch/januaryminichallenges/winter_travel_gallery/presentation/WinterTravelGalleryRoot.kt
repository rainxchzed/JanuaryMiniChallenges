package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WinterTravelGalleryRoot(
    viewModel: WinterTravelGalleryViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WinterTravelGalleryScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun WinterTravelGalleryScreen(
    state: WinterTravelGalleryState,
    onAction: (WinterTravelGalleryAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    WinterTravelGalleryScreen(
        state = WinterTravelGalleryState(),
        onAction = {}
    )
}