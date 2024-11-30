package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    private var currentFilter = Filter.empty
    private var applyButtonState = false

    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(FiltersState.Empty)
    val state: StateFlow<FiltersState>
        get() = _state

    fun getCurrentFilter() {
        val getFilters = getFiltersUseCase.execute()
        val getSearchFilters = getSearchFiltersUseCase.execute()

//        println("($getFilters)($getSearchFilters)")

        currentFilter = if (with(getFilters) {
                location.isNullOrEmpty() &&
                    industry?.name.isNullOrEmpty()
//                    salary == null &&
//                    !withoutSalaryButton
            })  getSearchFilters else getFilters

        applyButtonState = when {
            getFilters.location.isNullOrEmpty() || getFilters.location == getSearchFilters.location -> false
            getFilters.industry?.name.isNullOrEmpty() || getFilters.industry == getSearchFilters.industry -> false
            getFilters.salary == null || getFilters.salary == getSearchFilters.salary -> false
            !getFilters.withoutSalaryButton || getFilters.withoutSalaryButton == getSearchFilters.withoutSalaryButton -> false
            else -> true
        }

        _state.value = FiltersState.Data(currentFilter, applyButtonState)
    }


    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {

//        _state.value = FiltersState.Data(getFiltersUseCase.execute(), getSearchFiltersUseCase.execute())
    }

    fun getSearchFilters() = viewModelScope.launch(Dispatchers.Main) {
//        val searchFilter = FiltersState.Data(getSearchFiltersUseCase.execute(), getSearchFiltersUseCase.execute())
//        _state.value = searchFilter
    }

    fun setEmptyCountry() {
        val filters = getFiltersUseCase.execute().copy(area = null, region = null)
        setFiltersUseCase.execute(filters)
    }

    fun setEmptyIndustry() {
        val filters = getFiltersUseCase.execute().copy(industry = null)
        setFiltersUseCase.execute(filters)
    }

    fun setFilters(salary: Int?, withoutSalaryButton: Boolean) {
        val filters = getFiltersUseCase.execute()
            .copy(salary = salary, withoutSalaryButton = withoutSalaryButton)
        println("setFilters:${getFiltersUseCase.execute()}")
        setSearchFiltersUseCase.execute(filters)
    }

    fun setSearchFilters() {
        setSearchFiltersUseCase.execute(currentFilter)
    }

    fun clearFilters() {
        clearFiltersUseCase.execute()
        getFilters()
    }
}
