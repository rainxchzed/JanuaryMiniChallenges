package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.navigation

sealed interface WinterTravelGraph {
    data object MainScreen: WinterTravelGraph
    data class GalleryScreen (
        val destination: String
    ): WinterTravelGraph
}