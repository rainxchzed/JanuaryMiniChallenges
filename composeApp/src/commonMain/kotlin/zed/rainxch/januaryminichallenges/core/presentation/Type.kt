package zed.rainxch.januaryminichallenges.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import januaryminichallenges.composeapp.generated.resources.PlusJakartaSans_Bold
import januaryminichallenges.composeapp.generated.resources.PlusJakartaSans_Medium
import januaryminichallenges.composeapp.generated.resources.PlusJakartaSans_Regular
import januaryminichallenges.composeapp.generated.resources.PlusJakartaSans_SemiBold
import januaryminichallenges.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun plusJakartaSansFont(): FontFamily {
    return FontFamily(listOf(
        Font(Res.font.PlusJakartaSans_Regular, FontWeight.Normal),
        Font(Res.font.PlusJakartaSans_Medium, FontWeight.Medium),
        Font(Res.font.PlusJakartaSans_SemiBold, FontWeight.SemiBold),
        Font(Res.font.PlusJakartaSans_Bold, FontWeight.Bold),
    ))
}