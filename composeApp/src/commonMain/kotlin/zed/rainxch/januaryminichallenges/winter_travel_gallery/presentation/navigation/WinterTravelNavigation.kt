package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.WinterTravelGalleryAction
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.WinterTravelGalleryMainScreen
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.WinterTravelGalleryRoot

@Composable
fun WinterTravelNavigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = WinterTravelGraph.MainScreen
    ) {
        composable<WinterTravelGraph.MainScreen> {
            WinterTravelGalleryMainScreen()
        }

        composable<WinterTravelGraph.GalleryScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<WinterTravelGraph.GalleryScreen>()

            WinterTravelGalleryRoot()
        }
    }
}