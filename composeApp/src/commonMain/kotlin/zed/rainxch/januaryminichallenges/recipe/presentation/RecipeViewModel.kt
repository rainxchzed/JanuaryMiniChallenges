package zed.rainxch.januaryminichallenges.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.januaryminichallenges.recipe.presentation.RecipeEvent.*
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

    private var cacheData = _state.value.recipes

    private val _events = Channel<RecipeEvent>()
    val events = _events.receiveAsFlow()

    private fun loadRecipes() {
        viewModelScope.launch {
            val recipes = async(Dispatchers.Default) {
                Recipe.entries.map { it.toRecipeX() }
            }.await()

            cacheData = recipes

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
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isReloading = true,
                        )
                    }

                    delay(500)

                    _state.update {
                        it.copy(
                            recipes = it.recipes.filter { it.isFavourite } + it.recipes.filterNot { it.isFavourite }.shuffled(),
                            isReloading = false,
                        )
                    }
                }
            }

            is RecipeAction.OnRecipeClick -> {
                _state.update { it.copy(
                    selectedRecipe = action.recipeX
                ) }
            }

            RecipeAction.OnRecipeDone -> {
                _state.update { it.copy(
                    selectedRecipe = null
                ) }
            }

            is RecipeAction.OnRecipeFavouriteToggle -> {
                _state.update {
                    val newArray = it.recipes.map { recipe ->
                        if (recipe.recipe.title == action.recipeX.recipe.title) {
                            recipe.copy(
                                isFavourite = !recipe.isFavourite
                            )
                        } else recipe
                    }

                    cacheData = newArray

                    it.copy(
                        recipes = newArray
                    )
                }

                viewModelScope.launch {
                    _events.send(
                        OnMessage(
                            message = if (!action.recipeX.isFavourite) {
                                "Added to Favourites"
                            } else "Removed from Favourites"
                        )
                    )
                }
            }

            is RecipeAction.OnSearch -> {
                _state.update {
                    it.copy(
                        query = action.query,
                        recipes = if (action.query.isBlank()) {
                            cacheData
                        } else {
                            cacheData.filter { recipeX ->
                                recipeX.recipe.title
                                    .lowercase()
                                    .contains(action.query.lowercase())
                            }
                        }
                    )
                }
            }
        }
    }

}