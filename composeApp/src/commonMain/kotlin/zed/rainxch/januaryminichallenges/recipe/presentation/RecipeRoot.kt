package zed.rainxch.januaryminichallenges.recipe.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.januaryminichallenges.recipe.presentation.utils.ScreenSize
import zed.rainxch.januaryminichallenges.recipe.presentation.utils.rememberScreenSize

@Composable
fun RecipeRoot(
    viewModel: RecipeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecipeScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RecipeScreen(
    state: RecipeState,
    onAction: (RecipeAction) -> Unit,
) {
    val screenSize = rememberScreenSize()

    Scaffold(
        topBar = {
            RecipeTopBar(state, onAction)
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentPadding = innerPadding,
            columns = GridCells.Fixed(
                when (screenSize) {
                    ScreenSize.Compact -> 1
                    ScreenSize.Medium -> 2
                    ScreenSize.Expanded -> 2
                }
            )
        ) {
            items(state.recipes) { recipe ->

            }
        }
    }
}

@Composable
fun RecipeTopBar(
    state: RecipeState,
    onAction: (RecipeAction) -> Unit
) {

}

@Preview
@Composable
private fun Preview() {
    RecipeScreen(
        state = RecipeState(),
        onAction = {}
    )
}