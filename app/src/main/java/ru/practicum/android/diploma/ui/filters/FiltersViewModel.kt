package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.common.Source
import ru.practicum.android.diploma.common.Source.SEARCH
import ru.practicum.android.diploma.common.Source.INDUSTRY
import ru.practicum.android.diploma.common.Source.LOCATION
import ru.practicum.android.diploma.common.Source.FAVORITE
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.domain.usecase.filters.ClearFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetSearchFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetSearchFiltersUseCase

class FiltersViewModel(
    private val setFiltersUseCase: SetFiltersUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val clearFiltersUseCase: ClearFiltersUseCase,
    private val setSearchFiltersUseCase: SetSearchFiltersUseCase,
    private val getSearchFiltersUseCase: GetSearchFiltersUseCase
) : ViewModel() {

    private var currentFilter: Filter = getSearchFiltersUseCase.execute()
    private var storageFilter:Filter = getSearchFiltersUseCase.execute()

    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(FiltersState.Empty)
    val state: StateFlow<FiltersState>
        get() = _state

    fun getCurrentFilter(source: Source) = viewModelScope.launch(Dispatchers.Main) {
        when (source) {
            SEARCH -> getSearchFilter()
            FAVORITE -> {}
            INDUSTRY -> getFilters()
            LOCATION -> getFilters()
        }
    }

    private fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        val filters = getFiltersUseCase.execute()
        currentFilter = currentFilter.copy(area = filters.area, region = filters.region, industry = filters.industry)
        _state.value = FiltersState.Data(currentFilter, applyButtonVisible())
    }

    private fun getSearchFilter() = viewModelScope.launch(Dispatchers.Main) {
        setFiltersUseCase.execute(Filter.empty)
        storageFilter = getSearchFiltersUseCase.execute()
        currentFilter = storageFilter
        _state.value = FiltersState.Data(currentFilter, applyButtonVisible())
    }

    fun setCurrentSalary(salary: String) {
        currentFilter = currentFilter.copy(salary = salary.toIntOrNull())
        _state.value = FiltersState.Data(currentFilter.copy(), applyButtonVisible())
    }

    fun setWithoutSalaryButton(set: Boolean) {
        currentFilter = currentFilter.copy(withoutSalaryButton = set)
        _state.value = FiltersState.Data(currentFilter, applyButtonVisible())
    }

    private fun applyButtonVisible(): Boolean {
        return currentFilter.location != storageFilter.location ||
            currentFilter.industry != storageFilter.industry ||
            currentFilter.salary != storageFilter.salary ||
            currentFilter.withoutSalaryButton != storageFilter.withoutSalaryButton
    }

    fun setEmptyCountry() {
        setFiltersUseCase.execute(currentFilter.copy(area = null, region = null))
        getFilters()
    }

    fun setEmptyIndustry() {
        setFiltersUseCase.execute(currentFilter.copy(industry = null))
        getFilters()
    }

    fun setFilters(salary: Int?, withoutSalaryButton: Boolean) {
        val filters = getFiltersUseCase.execute()
            .copy(salary = salary, withoutSalaryButton = withoutSalaryButton)
        setSearchFiltersUseCase.execute(filters)
    }

    fun setSearchFilters() = viewModelScope.launch(Dispatchers.Main) {
        val filters = getFiltersUseCase.execute().copy(
            area = currentFilter.area,
            region = currentFilter.region,
            salary = currentFilter.salary,
            withoutSalaryButton = currentFilter.withoutSalaryButton
        )
        setSearchFiltersUseCase.execute(filters)
    }

    fun resetFilters() {
        clearFilters()
        setSearchFiltersUseCase.execute(Filter.empty)
        _state.value = FiltersState.Empty

//        clearFilters()
//        storageFilter=currentFilter
        currentFilter =storageFilter
//        _state.value = FiltersState.Empty
//        _state.value = FiltersState.Data(currentFilter, applyButtonVisible())
    }

    private fun clearFilters() {
        clearFiltersUseCase.execute()
    }
}
