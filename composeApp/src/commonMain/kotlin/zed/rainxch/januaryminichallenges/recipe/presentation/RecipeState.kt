package zed.rainxch.januaryminichallenges.recipe.presentation

import zed.rainxch.januaryminichallenges.recipe.presentation.model.RecipeX

data class RecipeState(
    val recipes: List<RecipeX> = emptyList()
)