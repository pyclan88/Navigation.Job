package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class FiltersViewModel(
    private val setFiltersUseCase: SetFiltersUseCase,
    private val getFiltersUseCase: GetFiltersUseCase
) : ViewModel() {

    val filters = getFiltersUseCase.execute()

    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(
        FiltersState(
            filters[1],
            filters[2],
            filters[3],
            filters[4].toBoolean()
        )
    )
    val state: StateFlow<FiltersState>
        get() = _state

    fun setFilters(){
        val filters = mutableListOf("40", "", "", "true")
        setFiltersUseCase.execute(filters)

    }
}
