package zed.rainxch.januaryminichallenges.core.data

import java.io.File

actual fun producePathForDataStore(): String {
    val file = File(System.getProperty("java.io.tmpdir"), dataStoreFileName)
    return file.absolutePath
}