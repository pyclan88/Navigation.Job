package ru.practicum.android.diploma.ui.industry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase

class IndustryViewModel(
    private val industriesUseCase: GetIndustriesUseCase
) : ViewModel() {

    // Добавил Loading, возможно этот state не нужен
    private val _state: MutableStateFlow<IndustryState> =
        MutableStateFlow(IndustryState.Loading)
    val state: StateFlow<IndustryState>
        get() = _state

    fun getIndustries() = viewModelScope.launch {
        val industries = industriesUseCase.execute()
        val industryState = when {
            industries.first?.isEmpty() == true -> IndustryState.Empty
            industries.second?.isNotEmpty() == true -> IndustryState.Error
            else -> IndustryState.Data(industries = industries.first!!)
        }
        _state.value = industryState
    }
}
