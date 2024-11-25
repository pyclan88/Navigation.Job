package ru.practicum.android.diploma.ui.location

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.state.FieldState

class LocationViewModel : ViewModel() {

    private val _state: MutableStateFlow<FieldState> = MutableStateFlow(
        // хардкод для теста
        FieldState(upperField = null, lowerField = "Москва")
    )
    val state: StateFlow<FieldState>
        get() = _state
}
