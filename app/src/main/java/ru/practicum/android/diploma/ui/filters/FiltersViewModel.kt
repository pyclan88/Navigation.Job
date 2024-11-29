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
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class FiltersViewModel(
    private val setFiltersUseCase: SetFiltersUseCase,
    private val getFiltersUseCase: GetFiltersUseCase,
    private val clearFiltersUseCase: ClearFiltersUseCase
) : ViewModel() {



    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(FiltersState.Empty)
    val state: StateFlow<FiltersState>
        get() = _state

    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        val filters = FiltersState.Data(getFiltersUseCase.execute())
        _state.value = filters
    }

    fun setEmptyCountry() {
        val filters = getFiltersUseCase.execute().copy(area = null, region = null)
        setFiltersUseCase.execute(filters)
        getFilters()
    }

    fun setEmptyIndustry() {
        val filters = getFiltersUseCase.execute().copy(industry = null)
        setFiltersUseCase.execute(filters)
        getFilters()
    }

    fun setFilters(salary: Int?, withoutSalaryButton: Boolean) {
        val filters = getFiltersUseCase.execute()
            .copy(salary = salary, withoutSalaryButton = withoutSalaryButton)
        setFiltersUseCase.execute(filters)
        getFilters()
    }

    fun clearFilters() {
        clearFiltersUseCase.execute()
        getFilters()
    }
}
