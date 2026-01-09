package zed.rainxch.januaryminichallenges.recipe.presentation

import zed.rainxch.januaryminichallenges.recipe.presentation.model.RecipeX

data class RecipeState(
    val query: String = "",
    val recipes: List<RecipeX> = emptyList(),
    val isReloading: Boolean = false,
    val selectedRecipe: RecipeX? = null
)