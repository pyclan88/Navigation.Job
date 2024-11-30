package ru.practicum.android.diploma.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.LocationState
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class LocationViewModel(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val setFiltersUseCase: SetFiltersUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<LocationState> = MutableStateFlow(
        LocationState.Empty
    )
    val state: StateFlow<LocationState>
        get() = _state

    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        val filters = getFiltersUseCase.execute()
        if (filters.area?.name.isNullOrEmpty() && filters.region?.name.isNullOrEmpty()) {
            _state.value = LocationState.Empty
        } else {
            _state.value = LocationState.Data(
                country = filters.area,
                region = filters.region
            )
        }
    }

    fun clearState() {
        _state.value = LocationState.Empty
    }

    fun setFilter(countryIsNotEmpty: Boolean, regionIsNotEmpty: Boolean) {
        val state = _state.value as? LocationState.Data
        val country = state?.takeIf { countryIsNotEmpty }?.country
        val region = state?.takeIf { regionIsNotEmpty }?.region

        val filters = getFiltersUseCase.execute().copy(area = country, region = region)
        setFiltersUseCase.execute(filters)
    }

    fun setEmptyFilter() {
        val filters = getFiltersUseCase.execute().copy(area = null, region = null)
        setFiltersUseCase.execute(filters)
    }
}

