package zed.rainxch.januaryminichallenges.recipe.presentation.model

import januaryminichallenges.composeapp.generated.resources.Res
import januaryminichallenges.composeapp.generated.resources.apple_cinnamon_brew
import januaryminichallenges.composeapp.generated.resources.baked_apple_crisp
import januaryminichallenges.composeapp.generated.resources.cinnamon_swirl_rolls
import januaryminichallenges.composeapp.generated.resources.creamy_mushroom_soup
import januaryminichallenges.composeapp.generated.resources.ginger_honey_tea
import januaryminichallenges.composeapp.generated.resources.hot_spiced_cocoa
import januaryminichallenges.composeapp.generated.resources.potato_leek_comfort_soup
import januaryminichallenges.composeapp.generated.resources.spiced_lentil_soup
import januaryminichallenges.composeapp.generated.resources.warm_banana_oat_muffins
import januaryminichallenges.composeapp.generated.resources.winter_vegetable_soup
import org.jetbrains.compose.resources.DrawableResource

data class RecipeX(
    val recipe: Recipe,
    val isFavourite: Boolean = false,
    val image: DrawableResource
)

fun Recipe.toRecipeX(): RecipeX {
    return RecipeX(
        recipe = this,
        isFavourite = false,
        image = when (this) {
            Recipe.CREAMY_MUSHROOM_SOUP -> Res.drawable.creamy_mushroom_soup
            Recipe.WINTER_VEGETABLE_STEW -> Res.drawable.winter_vegetable_soup
            Recipe.SPICED_LENTIL_SOUP -> Res.drawable.spiced_lentil_soup
            Recipe.POTATO_LEEK_COMFORT_SOUP -> Res.drawable.potato_leek_comfort_soup
            Recipe.GINGER_HONEY_TEA -> Res.drawable.ginger_honey_tea
            Recipe.HOT_SPICED_COCOA -> Res.drawable.hot_spiced_cocoa
            Recipe.APPLE_CINNAMON_BREW -> Res.drawable.apple_cinnamon_brew
            Recipe.WARM_BANANA_OAT_MUFFINS -> Res.drawable.warm_banana_oat_muffins
            Recipe.CINNAMON_SWIRL_ROLLS -> Res.drawable.cinnamon_swirl_rolls
            Recipe.BAKED_APPLE_CRISP -> Res.drawable.baked_apple_crisp
        }
    )
}