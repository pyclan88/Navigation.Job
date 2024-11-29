package ru.practicum.android.diploma.ui.industry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.common.AppConstants.CLICK_DEBOUNCE_DELAY
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.domain.state.IndustryState.Industries
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.Loading
import ru.practicum.android.diploma.domain.state.IndustryState.Input.Empty
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase
import ru.practicum.android.diploma.util.debounce

class IndustryViewModel(
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val setFiltersUseCase: SetFiltersUseCase
) : ViewModel() {

    private var industries: List<Industry> = mutableListOf()

    private val _state: MutableStateFlow<IndustryState> =
        MutableStateFlow(IndustryState(Empty, Loading))
    val state: StateFlow<IndustryState>
        get() = _state

    val searchDebounce: (String) -> Unit = debounce(
        delayMillis = CLICK_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText -> searchFilter(changedText) }

    fun getIndustries() = viewModelScope.launch {
        val response = getIndustriesUseCase.execute()
        industries = response.first ?: mutableListOf()
        val dataState = when {
            response.first?.isEmpty() == true -> Industries.Empty
            response.second?.isNotEmpty() == true -> Industries.Error
            else -> Industries.Data(industries = response.first!!)
        }
        _state.value = state.value.copy(data = dataState)
    }

    fun setFilters(industry: Industry) {
        val filters = getFiltersUseCase.execute()
            .copy(industry = industry)
        setFiltersUseCase.execute(filters)
    }

    private fun searchFilter(searchText: String) {
        val filteredIndustries = industries.filter {
            it.name.lowercase().contains(searchText.lowercase())
        }
        _state.value =
            if (filteredIndustries.isNotEmpty())
                state.value.copy(data = Industries.Data(filteredIndustries))
            else state.value.copy(
                data = Industries.Empty
            )
    }

    fun clearSearch() {
        _state.value = IndustryState(Empty, Industries.Data(industries))
    }
}
