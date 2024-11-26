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
    private val getIndustriesUseCase: GetIndustriesUseCase
) : ViewModel() {

    // Добавил Loading, возможно этот state не нужен
    private val _state: MutableStateFlow<IndustryState> =
        MutableStateFlow(IndustryState(Input.Empty, IndustriesList.Loading))
    val state: StateFlow<IndustryState>
        get() = _state

    fun getIndustries() = viewModelScope.launch {
        val industries = getIndustriesUseCase.execute()
        val industryState = when {
            industries.first?.isEmpty() == true -> state.value.copy(industries = IndustriesList.Empty)
            industries.second?.isNotEmpty() == true -> state.value.copy(industries = IndustriesList.Error)
            else -> state.value.copy(industries = IndustriesList.Data(industries = industries.first!!))
        }
        _state.value = industryState
    }
}
