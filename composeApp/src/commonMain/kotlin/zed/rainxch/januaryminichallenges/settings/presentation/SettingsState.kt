package zed.rainxch.januaryminichallenges.settings.presentation

data class SettingsState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)