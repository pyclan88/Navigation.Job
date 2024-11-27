package ru.practicum.android.diploma.ui.industry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.domain.state.IndustryState.Industries
import ru.practicum.android.diploma.domain.state.IndustryState.Input
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class IndustryViewModel(
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val setFiltersUseCase: SetFiltersUseCase
) : ViewModel() {

    private var getIndustryList: List<Industry> = mutableListOf()

    private val _state: MutableStateFlow<IndustryState> =
        MutableStateFlow(IndustryState(Input.Empty, Industries.Loading))
    val state: StateFlow<IndustryState>
        get() = _state

    fun getIndustries() = viewModelScope.launch {
        val industries = getIndustriesUseCase.execute()
        getIndustryList = industries.first ?: mutableListOf()
        val industryState = when {
            industries.first?.isEmpty() == true -> state.value.copy(data = Industries.Empty)
            industries.second?.isNotEmpty() == true -> state.value.copy(data = Industries.Error)
            else -> state.value.copy(data = Industries.Data(industries = industries.first!!))
        }
        _state.value = industryState
    }

    fun setFilters(industry: Industry) {
        val filters = getFiltersUseCase.execute()
            .copy(industry = industry)
        setFiltersUseCase.execute(filters)
    }

    fun selectIndustry(industryId: String) {

    }

    fun searchFilter(searchText: String) {
            _state.value = state.value.copy(data = Industries.Data(industries = getIndustryList.filter {
                it.name.lowercase().contains(searchText.lowercase())
            }))
    }
}
