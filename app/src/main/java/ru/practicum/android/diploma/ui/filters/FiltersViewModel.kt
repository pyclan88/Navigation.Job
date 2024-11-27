package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun getFilters() {
        _state.value = FiltersState.Data(getFiltersUseCase.execute())
        println("123"+_state.value)
    }

    fun setFilters(salary: Int?, withoutSalaryButton: Boolean) {
        val filters = getFiltersUseCase.execute()
            .copy(salary = salary, withoutSalaryButton = withoutSalaryButton)
        setFiltersUseCase.execute(filters)
    }

    fun clearFilters() {
        clearFiltersUseCase.execute()
        getFilters()
    }
}
