package zed.rainxch.januaryminichallenges.settings.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.januaryminichallenges.core.data.createDataStore
import zed.rainxch.januaryminichallenges.core.data.producePathForDataStore
import zed.rainxch.januaryminichallenges.core.presentation.SettingsColors
import zed.rainxch.januaryminichallenges.core.presentation.plusJakartaSansFont
import zed.rainxch.januaryminichallenges.settings.data.repository.SettingsRepositoryImpl

@Composable
fun SettingsRoot(
    viewModel: SettingsViewModel = viewModel(factory = viewModelFactory {
        initializer {
            SettingsViewModel(
                settingsRepository = SettingsRepositoryImpl(createDataStore(producePath = {
                    producePathForDataStore()
                }))
            )
        }
    })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state,
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsState,
    uiState: UiState,
    onAction: (SettingsAction) -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .background(SettingsColors.bg)
            ) {
                OutlinedIconButton(
                    onClick = { },
                    border = BorderStroke(
                        width = 1.dp,
                        color = SettingsColors.outline
                    ),
                    modifier = Modifier.size(35.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Settings",
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.SemiBold,
                    color = SettingsColors.textPrimary,
                    fontSize = 28.sp
                )

                Spacer(Modifier.height(24.dp))
            }
        },
        containerColor = SettingsColors.bg,
        modifier = Modifier
            .fillMaxSize()
            .background(SettingsColors.bg)
            .padding(16.dp),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .graphicsLayer {
                    clip = false
                }
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Notification(state, onAction)

            NewYearTheme(state, uiState, onAction)

            DailyStepGoal(state, uiState, onAction)

            MotivationLevel(state, onAction)

            LastUpdate(state)
        }
    }
}

@Composable
fun LastUpdate(
    state: SettingsState
) {
    Text(
        text = "Last update: ${state.lastUpdated}",
        fontFamily = plusJakartaSansFont(),
        fontWeight = FontWeight.Normal,
        color = SettingsColors.textSecondary,
        fontSize = 14.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotivationLevel(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = SettingsColors.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 18.dp,
                )
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Motivation Level",
                fontFamily = plusJakartaSansFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = SettingsColors.textSecondary
            )

            Slider(
                value = state.motivationLevel,
                onValueChange = {
                    onAction(SettingsAction.OnMotivationLevelChange(it))
                },
                steps = 9,
                colors = SliderDefaults.colors(
                    activeTrackColor = SettingsColors.primary,
                    inactiveTrackColor = SettingsColors.outlineInput,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                ),
                thumb = {
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height(36.dp)
                            .clip(CircleShape)
                            .background(SettingsColors.surface)
                            .border(2.dp, SettingsColors.primary, CircleShape)
                    )
                }
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0.0",
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.Normal,
                    color = SettingsColors.textSecondary,
                    fontSize = 16.sp
                )

                Text(
                    text = "0.5",
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.SemiBold,
                    color = SettingsColors.textPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = SettingsColors.outline,
                            shape = CircleShape
                        )
                        .padding(vertical = 2.dp, horizontal = 10.dp)
                )

                Text(
                    text = "1.0",
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.Normal,
                    color = SettingsColors.textSecondary,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun DailyStepGoal(
    state: SettingsState,
    uiState: UiState,
    onAction: (SettingsAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = SettingsColors.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Daily Step Goal",
                fontFamily = plusJakartaSansFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = SettingsColors.textSecondary
            )

            OutlinedTextField(
                value = uiState.tempGoal ?: state.goal,
                onValueChange = {
                    onAction(SettingsAction.OnGoalChange(it))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SettingsColors.surfaceInput,
                    unfocusedContainerColor = SettingsColors.surfaceInput,
                    focusedBorderColor = SettingsColors.primary,
                    unfocusedBorderColor = SettingsColors.outlineInput,
                    cursorColor = SettingsColors.primary
                ),
                textStyle = TextStyle(
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = SettingsColors.textPrimary
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onAction(SettingsAction.OnGoalAction)
                        focusManager.clearFocus(true)
                        keyboard?.hide()
                    }
                )
            )
        }
    }
}

@Composable
private fun Notification(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = SettingsColors.surface
        ),
        modifier = Modifier.graphicsLayer {
            clip = false
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Winter Notification",
                fontFamily = plusJakartaSansFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = SettingsColors.textPrimary
            )

            Switch(
                checked = state.notificationOn,
                onCheckedChange = {
                    onAction(SettingsAction.OnNotificationToggle)
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = SettingsColors.primary,
                    uncheckedTrackColor = SettingsColors.outline,
                    checkedThumbColor = SettingsColors.surface,
                    uncheckedThumbColor = SettingsColors.surface,
                    checkedBorderColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
private fun NewYearTheme(
    state: SettingsState,
    uiState: UiState,
    onAction: (SettingsAction) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = SettingsColors.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "New Year Theme",
                fontFamily = plusJakartaSansFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = SettingsColors.textSecondary
            )

            var containerWidth by remember {
                mutableStateOf(0.dp)
            }
            val density = LocalDensity.current

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        containerWidth = with(density) {
                            it.size.width.toDp()
                        }
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .then(
                        if (uiState.isSelectThemeDropdownExpanded) {
                            Modifier.border(
                                width = 2.dp,
                                color = SettingsColors.primary,
                                shape = RoundedCornerShape(10.dp)
                            )
                        } else Modifier
                    )
                    .clickable(onClick = {
                        onAction(SettingsAction.OnToggleThemeDropdown)
                    })
                    .background(SettingsColors.surfaceInput)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.selectedTheme.label(),
                    fontFamily = plusJakartaSansFont(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = SettingsColors.textPrimary
                )

                Icon(
                    imageVector = if (uiState.isSelectThemeDropdownExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = SettingsColors.textPrimary
                )
            }

            ThemeDropdown(
                isExpanded = uiState.isSelectThemeDropdownExpanded,
                selectedTheme = state.selectedTheme,
                onDismissRequest = {
                    onAction(SettingsAction.OnToggleThemeDropdown)
                },
                onOptionSelected = {
                    onAction(SettingsAction.OnThemeSelected(it))
                },
                containerWidth = containerWidth
            )
        }
    }
}

@Composable
private fun ThemeDropdown(
    isExpanded: Boolean,
    selectedTheme: NewYearTheme,
    onDismissRequest: () -> Unit,
    onOptionSelected: (NewYearTheme) -> Unit,
    modifier: Modifier = Modifier,
    containerWidth: Dp
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .width(containerWidth),
        offset = DpOffset(x = 0.dp, y = 8.dp),
        containerColor = SettingsColors.surfaceInput,
        border = BorderStroke(
            width = 1.dp, color = SettingsColors.outlineInput
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        NewYearTheme.entries.forEach { theme ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = theme.label(),
                        fontFamily = plusJakartaSansFont(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = SettingsColors.textPrimary
                    )
                },
                trailingIcon = {
                    if (selectedTheme == theme) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = SettingsColors.primary
                        )
                    }
                },
                onClick = {
                    onOptionSelected(theme)
                },
                contentPadding = PaddingValues(
                    horizontal = 8.dp, vertical = 10.dp
                )
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SettingsScreen(
        state = SettingsState(),
        uiState = UiState(),
        onAction = {}
    )
}