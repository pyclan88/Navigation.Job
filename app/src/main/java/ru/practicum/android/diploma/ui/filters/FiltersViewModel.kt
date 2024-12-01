package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.domain.usecase.filters.ClearFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetTmpFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetTmpFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetSearchFiltersUseCase

class FiltersViewModel(
    private val setTmpFiltersUseCase: SetTmpFiltersUseCase,
    private val getTmpFiltersUseCase: GetTmpFiltersUseCase,
    private val clearFiltersUseCase: ClearFiltersUseCase,
    private val setSearchFiltersUseCase: SetSearchFiltersUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(FiltersState.Empty)
    val state: StateFlow<FiltersState>
        get() = _state

    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        _state.value = FiltersState.Data(getTmpFiltersUseCase.execute())
    }

    fun setEmptyCountry() {
        val filters = getTmpFiltersUseCase.execute().copy(location = null)
        setTmpFiltersUseCase.execute(filters)
    }

    fun setEmptyIndustry() {
        val filters = getTmpFiltersUseCase.execute().copy(industry = null)
        setTmpFiltersUseCase.execute(filters)
    }

    fun setFilters(salary: Int?, withoutSalaryButton: Boolean) {
        val filters = getTmpFiltersUseCase.execute()
            .copy(salary = salary, withoutSalaryButton = withoutSalaryButton)
        setSearchFiltersUseCase.execute(filters)
    }

    fun clearFilters() {
        clearFiltersUseCase.execute()
        getFilters()
    }
}
