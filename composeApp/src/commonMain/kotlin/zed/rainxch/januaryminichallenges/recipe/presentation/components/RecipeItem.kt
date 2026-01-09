package zed.rainxch.januaryminichallenges.recipe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.januaryminichallenges.core.presentation.RecipeColors
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSerifFont
import zed.rainxch.januaryminichallenges.recipe.presentation.model.RecipeX

@Composable
fun RecipeItem(
    recipeX: RecipeX,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Image(
                painter = painterResource(recipeX.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = {
                    onFavouriteClick()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = RecipeColors.overlay,
                    contentColor = RecipeColors.textOnPrimary
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .size(44.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if(recipeX.isFavourite) {
                        Icons.Default.Favorite
                    } else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                )
            }
        }

        Text(
            text = recipeX.recipe.title,
            fontFamily = instrumentSerifFont(),
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            color = RecipeColors.textPrimary
        )
    }
}