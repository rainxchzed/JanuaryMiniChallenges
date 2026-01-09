package zed.rainxch.januaryminichallenges.recipe.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.januaryminichallenges.core.presentation.RecipeColors
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSerifFont
import zed.rainxch.januaryminichallenges.core.presentation.utils.ObserveAsEvents
import zed.rainxch.januaryminichallenges.recipe.presentation.components.RecipeDialog
import zed.rainxch.januaryminichallenges.recipe.presentation.components.RecipeSnackbar
import zed.rainxch.januaryminichallenges.recipe.presentation.components.RecipeItem
import zed.rainxch.januaryminichallenges.recipe.presentation.components.SearchTextField
import zed.rainxch.januaryminichallenges.recipe.presentation.utils.ScreenSize
import zed.rainxch.januaryminichallenges.recipe.presentation.utils.rememberScreenSize

@Composable
fun RecipeRoot(
    viewModel: RecipeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RecipeEvent.OnMessage -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    RecipeScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = viewModel::onAction
    )

    state.selectedRecipe?.let {
        RecipeDialog(
            recipeX = state.selectedRecipe!!,
            onDismissRequest = {
                viewModel.onAction(RecipeAction.OnRecipeDone)
            }
        )
    }
}

@Composable
fun RecipeScreen(
    state: RecipeState,
    snackbarHostState: SnackbarHostState,
    onAction: (RecipeAction) -> Unit,
) {
    val screenSize = rememberScreenSize()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { data ->
                    RecipeSnackbar(data)
                }
            )
        },
        containerColor = RecipeColors.bg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Text(
                    text = "January Recipe",
                    fontFamily = instrumentSerifFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    color = RecipeColors.textPrimary
                )

                Spacer(Modifier.height(20.dp))

                SearchTextField(
                    value = state.query,
                    onValueChange = {
                        onAction(RecipeAction.OnSearch(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (state.recipes.isNotEmpty()) {
                PullToRefreshBox(
                    isRefreshing = state.isReloading,
                    onRefresh = {
                        onAction(RecipeAction.OnPullToRefreshTrigger)
                    }
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        columns = GridCells.Fixed(
                            when (screenSize) {
                                ScreenSize.Compact -> 1
                                ScreenSize.Medium, ScreenSize.Expanded -> 2
                            }
                        ),
                        verticalArrangement = Arrangement.spacedBy(28.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(
                            items = state.recipes.filter { it.isFavourite },
                            key = { it.recipe.title }
                        ) { recipe ->
                            RecipeItem(
                                recipeX = recipe,
                                onClick = {
                                    onAction(RecipeAction.OnRecipeClick(recipe))
                                },
                                onFavouriteClick = {
                                    onAction(RecipeAction.OnRecipeFavouriteToggle(recipe))
                                },
                                modifier = Modifier.animateItem()
                            )
                        }

                        items(
                            items = state.recipes.filterNot { it.isFavourite },
                            key = { it.recipe.title }
                        ) { recipe ->
                            RecipeItem(
                                recipeX = recipe,
                                onClick = {
                                    onAction(RecipeAction.OnRecipeClick(recipe))
                                },
                                onFavouriteClick = {
                                    onAction(RecipeAction.OnRecipeFavouriteToggle(recipe))
                                },
                                modifier = Modifier.animateItem()
                            )
                        }
                    }
                }
            } else {
                Spacer(Modifier.height(16.dp))

                Text(
                    text = "No recipes match your search",
                    fontFamily = instrumentSerifFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    RecipeScreen(
        state = RecipeState(),
        snackbarHostState = SnackbarHostState(),
        onAction = {},
    )
}