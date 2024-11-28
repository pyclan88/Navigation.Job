package ru.practicum.android.diploma.ui.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.domain.state.RegionState
import ru.practicum.android.diploma.domain.state.RegionState.Data
import ru.practicum.android.diploma.domain.state.RegionState.Input
import ru.practicum.android.diploma.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class RegionViewModel(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val setFiltersUseCase: SetFiltersUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<RegionState> =
        MutableStateFlow(RegionState(Input.Empty, Data.Loading))
    val state: StateFlow<RegionState>
        get() = _state

    fun getRegions() = viewModelScope.launch {
        val countries = getCountriesUseCase.execute()
        val dataState = when {
            countries.first?.isEmpty() == true -> Data.Empty
            countries.second?.isNotEmpty() == true -> Data.Error
            else -> parseRegions(countries.first!!)
        }

        _state.value = state.value.copy(data = dataState)
    }

    fun setFilter(region: Region) {
        val filters = getFiltersUseCase.execute()
            .copy(region = region)
        setFiltersUseCase.execute(filters)
    }

    private fun parseRegions(countries: List<Country>): Data {
        val filter = getFiltersUseCase.execute()
        val regions = if (filter.area == null) {
            countries.flatMap { it.regions }
        } else {
            filter.area.regions
        }
        return Data.Data(regions)
    }
}
