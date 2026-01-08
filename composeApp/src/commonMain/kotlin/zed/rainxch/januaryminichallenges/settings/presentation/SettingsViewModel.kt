package zed.rainxch.januaryminichallenges.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.januaryminichallenges.settings.domain.repository.SettingsRepository
import zed.rainxch.januaryminichallenges.settings.presentation.utils.toFormattedDate

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val state = combine(
        flow = settingsRepository.getIsNotificationEnabled(),
        flow2 = settingsRepository.getMotivation(),
        flow3 = settingsRepository.getTheme(),
        flow4 = settingsRepository.getDailyGoal(),
        flow5 = settingsRepository.getLastUpdated()
    ) { notification, motivation, theme, goal, lastUpdated ->
        println("from combine: $goal")
        SettingsState(
            notificationOn = notification,
            motivationLevel = motivation,
            selectedTheme = theme,
            goal = goal,
            lastUpdated = lastUpdated.toFormattedDate()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = SettingsState()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: SettingsAction) {
        when (action) {
            SettingsAction.OnNotificationToggle -> {
                viewModelScope.launch {
                    settingsRepository.onToggleNotification()
                    settingsRepository.setLastUpdatedToNow()
                }
            }

            is SettingsAction.OnThemeSelected -> {
                viewModelScope.launch {
                    settingsRepository.onChangeTheme(action.theme)
                    settingsRepository.setLastUpdatedToNow()
                }
                _uiState.update {
                    it.copy(isSelectThemeDropdownExpanded = false)
                }
            }

            SettingsAction.OnToggleThemeDropdown -> {
                _uiState.update {
                    it.copy(
                        isSelectThemeDropdownExpanded = !it.isSelectThemeDropdownExpanded
                    )
                }
            }

            is SettingsAction.OnGoalChange -> {
                _uiState.update {
                    it.copy(tempGoal = action.goal)
                }
            }

            SettingsAction.OnGoalAction -> {
                viewModelScope.launch {
                    val goalToSave = _uiState.value.tempGoal ?: state.value.goal
                    settingsRepository.onChangeDailyGoal(goalToSave)
                    settingsRepository.setLastUpdatedToNow()
                    _uiState.update { it.copy(tempGoal = null) }
                }
            }

            is SettingsAction.OnMotivationLevelChange -> {
                viewModelScope.launch {
                    settingsRepository.onChangeMotivation(action.level)
                    settingsRepository.setLastUpdatedToNow()
                }
            }
        }
    }
}

data class UiState(
    val isSelectThemeDropdownExpanded: Boolean = false,
    val tempGoal: String? = null
)