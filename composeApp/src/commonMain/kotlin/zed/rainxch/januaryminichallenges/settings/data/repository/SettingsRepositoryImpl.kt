package zed.rainxch.januaryminichallenges.settings.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zed.rainxch.januaryminichallenges.settings.domain.repository.SettingsRepository
import zed.rainxch.januaryminichallenges.settings.presentation.NewYearTheme
import zed.rainxch.januaryminichallenges.settings.presentation.fromDSKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private val NOTIFICATION_ENABLED = booleanPreferencesKey("NOTIFICATION_ENABLED")
    private val NEW_YEAR_THEME = stringPreferencesKey("NEW_YEAR_THEME")
    private val DAILY_STEP_GOAL = stringPreferencesKey("DAILY_STEP_GOAL")
    private val MOTIVATION_LEVEL = floatPreferencesKey("MOTIVATION_LEVEL")
    private val LAST_UPDATE = longPreferencesKey("LAST_UPDATE")


    override fun getIsNotificationEnabled(): Flow<Boolean> {
        return dataStore.data.map {
            it[NOTIFICATION_ENABLED] == true
        }
    }

    override suspend fun onToggleNotification() {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[NOTIFICATION_ENABLED] = prefs[NOTIFICATION_ENABLED] != true
            }
        }
    }

    override fun getTheme(): Flow<NewYearTheme> {
        return dataStore.data.map {
            fromDSKey(it[NEW_YEAR_THEME] ?: NewYearTheme.CozyFireplace.ds_key())
        }
    }

    override suspend fun onChangeTheme(theme: NewYearTheme) {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[NEW_YEAR_THEME] = theme.ds_key()
            }
        }
    }

    override fun getDailyGoal(): Flow<String> {
        return dataStore.data.map {
            it[DAILY_STEP_GOAL] ?: ""
        }
    }

    override suspend fun onChangeDailyGoal(goal: String) {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[DAILY_STEP_GOAL] = goal
            }
        }
    }

    override fun getMotivation(): Flow<Float> {
        return dataStore.data.map {
            it[MOTIVATION_LEVEL] ?: .5f
        }
    }

    override suspend fun onChangeMotivation(motivation: Float) {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[MOTIVATION_LEVEL] = motivation
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    override fun getLastUpdated(): Flow<Long> {
        return dataStore.data.map {
            it[LAST_UPDATE] ?: Clock.System.now().toEpochMilliseconds()
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun setLastUpdatedToNow() {
        dataStore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[LAST_UPDATE] = Clock.System.now().toEpochMilliseconds()
            }
        }
    }
}