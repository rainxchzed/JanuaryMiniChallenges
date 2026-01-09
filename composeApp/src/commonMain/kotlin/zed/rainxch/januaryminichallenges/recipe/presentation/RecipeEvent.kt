package zed.rainxch.januaryminichallenges.recipe.presentation

sealed interface RecipeEvent {
    data class OnMessage(val message: String) : RecipeEvent
}