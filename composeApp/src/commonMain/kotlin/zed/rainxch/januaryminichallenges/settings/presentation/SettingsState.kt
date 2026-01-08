package zed.rainxch.januaryminichallenges.settings.presentation

data class SettingsState(
    val notificationOn: Boolean = false,
    val selectedTheme: NewYearTheme = NewYearTheme.CozyFireplace,
    val goal: String = "",
    val motivationLevel: Float = 0.5f,
    val lastUpdated: String = ""
)

enum class NewYearTheme {
    CozyFireplace,
    SnowyMorning,
    MidnightBlue;

    fun label(): String {
        return when (this) {
            CozyFireplace -> "Cozy Fireplace"
            SnowyMorning -> "Snowy Morning"
            MidnightBlue -> "Midnight Blue"
        }
    }
    fun ds_key(): String {
        return when (this) {
            CozyFireplace -> "Cozy Fireplace"
            SnowyMorning -> "Snowy Morning"
            MidnightBlue -> "Midnight Blue"
        }
    }
}

fun fromDSKey(key: String): NewYearTheme {
    return NewYearTheme.entries.find { it.ds_key() == key }!!
}