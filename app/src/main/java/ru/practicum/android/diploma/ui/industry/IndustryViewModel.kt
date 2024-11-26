package ru.practicum.android.diploma.ui.industry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.domain.state.IndustryState.IndustriesList
import ru.practicum.android.diploma.domain.state.IndustryState.Input
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase

class IndustryViewModel(
    private val industriesUseCase: GetIndustriesUseCase
) : ViewModel() {

    // Добавил Loading, возможно этот state не нужен
    private val _state: MutableStateFlow<IndustryState> =
        MutableStateFlow(IndustryState(Input.Empty, IndustriesList.Loading))
    val state: StateFlow<IndustryState>
        get() = _state

    fun getIndustries() = viewModelScope.launch {
        val industries = industriesUseCase.execute()
        val industryState = when {
            industries.first?.isEmpty() == true -> state.value.copy(industriesList = IndustriesList.Empty)
            industries.second?.isNotEmpty() == true -> state.value.copy(industriesList = IndustriesList.Error)
            else -> state.value.copy(industriesList = IndustriesList.Data(industries = industries.first!!))
        }
        _state.value = industryState
    }
}
