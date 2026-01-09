package zed.rainxch.januaryminichallenges.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.januaryminichallenges.recipe.presentation.model.Recipe
import zed.rainxch.januaryminichallenges.recipe.presentation.model.toRecipeX

class RecipeViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RecipeState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadRecipes()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RecipeState()
        )

    private fun loadRecipes() {
        viewModelScope.launch {
            val recipes = async(Dispatchers.Default) {
                Recipe.entries.map { it.toRecipeX() }
            }.await()

            _state.update {
                it.copy(
                    recipes = recipes
                )
            }
        }
    }

    fun onAction(action: RecipeAction) {
        when (action) {
            RecipeAction.OnPullToRefreshTrigger -> {

            }
            is RecipeAction.OnRecipeClick -> {

            }
            RecipeAction.OnRecipeDone -> {

            }
            is RecipeAction.OnRecipeFavouriteToggle -> {

            }
            is RecipeAction.OnSearch -> {

            }
        }
    }

}