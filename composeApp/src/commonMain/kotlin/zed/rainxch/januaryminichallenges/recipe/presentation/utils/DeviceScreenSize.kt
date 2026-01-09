package zed.rainxch.januaryminichallenges.recipe.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

enum class ScreenSize { Compact, Medium, Expanded }

@Composable
fun rememberScreenSize(): ScreenSize {
    val windowSizeInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val width = with(density) {
        windowSizeInfo.containerSize.width.toDp()
    }

    return when {
        width >= 840.dp -> ScreenSize.Expanded
        width >= 600.dp -> ScreenSize.Medium
        else -> ScreenSize.Compact
    }
}
