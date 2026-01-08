package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.WinterTravelGalleryMainScreen
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.WinterTravelGalleryRoot
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.model.Destination
import zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation.model.getByTitle

@Composable
fun WinterTravelNavigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = WinterTravelGraph.MainScreen,
        enterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Up)
        },
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Down)
        },
    ) {
        composable<WinterTravelGraph.MainScreen> {
            WinterTravelGalleryMainScreen(
                onNavigateToGallery = {
                    navHostController.navigate(WinterTravelGraph.GalleryScreen(it))
                }
            )
        }

        composable<WinterTravelGraph.GalleryScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<WinterTravelGraph.GalleryScreen>()

            WinterTravelGalleryRoot(
                destination = args.destination,
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}