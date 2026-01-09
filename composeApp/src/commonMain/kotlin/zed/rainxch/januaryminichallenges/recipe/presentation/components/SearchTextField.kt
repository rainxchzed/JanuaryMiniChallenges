package zed.rainxch.januaryminichallenges.recipe.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.januaryminichallenges.core.presentation.RecipeColors
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSansFont

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = RecipeColors.textPrimary,
            unfocusedBorderColor = RecipeColors.outline,
            focusedContainerColor = RecipeColors.surfaceInput,
            unfocusedContainerColor = RecipeColors.surfaceInput,
            focusedTextColor = RecipeColors.textPrimary,
            focusedPlaceholderColor = RecipeColors.textSecondary,
            unfocusedPlaceholderColor = RecipeColors.textSecondary,
            cursorColor = RecipeColors.textPrimary
        ),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = RecipeColors.textSecondary
                )

                Text(
                    text = "Search for recipes",
                    fontFamily = instrumentSansFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = RecipeColors.textSecondary
                )
            }
        }
    )
}