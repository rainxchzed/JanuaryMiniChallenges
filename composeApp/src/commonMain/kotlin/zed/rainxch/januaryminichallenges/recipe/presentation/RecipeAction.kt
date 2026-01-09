package zed.rainxch.januaryminichallenges.recipe.presentation

import zed.rainxch.januaryminichallenges.recipe.presentation.model.RecipeX

sealed interface RecipeAction {
    data class OnSearch(val query: String) : RecipeAction
    data object OnRecipeDone : RecipeAction
    data class OnRecipeClick(val recipeX: RecipeX) : RecipeAction
    data class OnRecipeFavouriteToggle(val recipeX: RecipeX) : RecipeAction
    data object OnPullToRefreshTrigger : RecipeAction
}