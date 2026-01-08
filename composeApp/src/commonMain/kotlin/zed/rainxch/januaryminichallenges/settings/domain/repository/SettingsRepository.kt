package zed.rainxch.januaryminichallenges.settings.domain.repository

import kotlinx.coroutines.flow.Flow
import zed.rainxch.januaryminichallenges.settings.presentation.NewYearTheme

interface SettingsRepository {
    fun getIsNotificationEnabled() : Flow<Boolean>
    suspend fun onToggleNotification()

    fun getTheme() : Flow<NewYearTheme>
    suspend fun onChangeTheme(theme: NewYearTheme)

    fun getDailyGoal() : Flow<String>
    suspend fun onChangeDailyGoal(goal: String)

    fun getMotivation() : Flow<Float>
    suspend fun onChangeMotivation(motivation: Float)

    fun getLastUpdated() : Flow<Long>
    suspend fun setLastUpdatedToNow()
}