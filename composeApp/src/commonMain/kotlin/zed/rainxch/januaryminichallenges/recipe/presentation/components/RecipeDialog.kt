package zed.rainxch.januaryminichallenges.recipe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.januaryminichallenges.core.presentation.RecipeColors
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSansFont
import zed.rainxch.januaryminichallenges.core.presentation.instrumentSerifFont
import zed.rainxch.januaryminichallenges.recipe.presentation.model.RecipeX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDialog(
    recipeX: RecipeX,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
            .widthIn(
                max = 520.dp,
                min = 380.dp
            )
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(RecipeColors.bg)
            .padding(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(recipeX.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Spacer(Modifier.height(8.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = recipeX.recipe.title,
                    fontFamily = instrumentSerifFont(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                    color = RecipeColors.textPrimary
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = recipeX.recipe.ingredients,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = RecipeColors.textSecondary,
                    fontFamily = instrumentSansFont(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = recipeX.recipe.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = RecipeColors.textSecondary,
                    fontFamily = instrumentSansFont(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        onDismissRequest()
                    },
                    border = BorderStroke(
                        1.dp, RecipeColors.outline
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Done",
                        fontFamily = instrumentSansFont(),
                        fontWeight = FontWeight.SemiBold,
                        color = RecipeColors.textSecondary,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}