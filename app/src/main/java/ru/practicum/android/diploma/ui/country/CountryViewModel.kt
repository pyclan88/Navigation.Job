package ru.practicum.android.diploma.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.CountryState
import ru.practicum.android.diploma.domain.usecase.GetCountriesUseCase

class CountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<CountryState> =
        MutableStateFlow(CountryState.Loading)
    val state: StateFlow<CountryState>
        get() = _state

    fun getCountries() = viewModelScope.launch {
        val countries = getCountriesUseCase.execute()
        val industryState = when {
            countries.first?.isEmpty() == true -> CountryState.Empty
            countries.second?.isNotEmpty() == true -> CountryState.Error
            else -> CountryState.Data(countries = countries.first!!)
        }
        _state.value = industryState
    }
}
