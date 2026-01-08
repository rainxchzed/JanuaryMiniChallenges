package zed.rainxch.januaryminichallenges.settings.presentation.utils

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import zed.rainxch.januaryminichallenges.core.data.dataStoreFileName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Long.toFormattedDate(): String {
    val instant = Instant.fromEpochMilliseconds(this)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val monthName = dateTime.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    return "$monthName ${dateTime.day}, ${dateTime.year} at ${dateTime.hour}:${dateTime.minute}"
}