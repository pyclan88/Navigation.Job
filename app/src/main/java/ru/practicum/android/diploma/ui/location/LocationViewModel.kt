package ru.practicum.android.diploma.ui.location

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.state.FieldState
import ru.practicum.android.diploma.domain.state.FieldState.Field.*

class LocationViewModel() : ViewModel() {

    private val _state: MutableStateFlow<FieldState> = MutableStateFlow(
        //хардкод для теста
        FieldState(Filled("Россия"), Filled("Москва"))
    )
    val state: StateFlow<FieldState>
        get() = _state

    fun clearField(isUpperField: Boolean) {
        val currentState = state.value
        val newState = if (isUpperField) {
            currentState.copy(upperField = Empty)
        } else {
            currentState.copy(lowerField = Empty)
        }
        _state.value = newState
    }
}
