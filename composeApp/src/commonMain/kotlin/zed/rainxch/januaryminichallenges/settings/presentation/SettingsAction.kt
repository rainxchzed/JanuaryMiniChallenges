package zed.rainxch.januaryminichallenges.settings.presentation

sealed interface SettingsAction {
    data object OnNotificationToggle : SettingsAction
    data class OnThemeSelected(val theme: NewYearTheme) : SettingsAction
    data class OnGoalChange(val goal: String) : SettingsAction
    data object OnGoalAction : SettingsAction
    data class OnMotivationLevelChange(val level: Float) : SettingsAction
    data object OnToggleThemeDropdown : SettingsAction
}