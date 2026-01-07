package zed.rainxch.januaryminichallenges.winter_travel_gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class WinterTravelGalleryViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(WinterTravelGalleryState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = WinterTravelGalleryState()
        )

    fun onAction(action: WinterTravelGalleryAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}