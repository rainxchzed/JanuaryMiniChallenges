package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.navigation

import kotlinx.serialization.Serializable
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.model.Destination

@Serializable
sealed interface WinterTravelGraph {
    @Serializable
    data object MainScreen: WinterTravelGraph
    @Serializable
    data class GalleryScreen (
        val destination: Destination
    ): WinterTravelGraph
}