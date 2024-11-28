package ru.practicum.android.diploma.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.LocationState
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase

class LocationViewModel(
    private val getFiltersUseCase: GetFiltersUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<LocationState> = MutableStateFlow(
        LocationState(country = null, region = null)
    )
    val state: StateFlow<LocationState>
        get() = _state

    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        val filters = getFiltersUseCase.execute()
        _state.value = LocationState(
            country = filters.area?.name,
            region = filters.region?.name
        )
    }
}
