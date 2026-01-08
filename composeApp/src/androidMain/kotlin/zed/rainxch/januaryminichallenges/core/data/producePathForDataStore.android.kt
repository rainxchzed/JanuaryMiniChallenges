package zed.rainxch.januaryminichallenges.core.data

import android.content.Context

// THIS IS TEMPORARILY :)
object ContextHolder {
    var context: Context? = null
}

actual fun producePathForDataStore(): String {
    return ContextHolder.context!!.filesDir.resolve(dataStoreFileName).absolutePath
}