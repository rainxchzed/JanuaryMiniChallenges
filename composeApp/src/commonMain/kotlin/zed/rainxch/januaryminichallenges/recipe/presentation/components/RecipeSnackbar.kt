package zed.rainxch.januaryminichallenges.recipe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.januaryminichallenges.core.presentation.RecipeColors
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSansFont

@Composable
fun RecipeSnackbar(
    data: SnackbarData,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.FavoriteBorder
) {
    Row (
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(RecipeColors.textPrimary)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = RecipeColors.textOnPrimary
        )

        Text(
            text = data.visuals.message,
            fontFamily = instrumentSansFont(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = RecipeColors.textOnPrimary
        )
    }
}