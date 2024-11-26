package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

class FiltersViewModel(
    private val setFiltersUseCase: SetFiltersUseCase,
    private val getFiltersUseCase: GetFiltersUseCase
) : ViewModel() {

    private val getFilters = getFiltersUseCase.execute()

    private val _state: MutableStateFlow<FiltersState> = MutableStateFlow(
        FiltersState(getFilters)
    )
    val state: StateFlow<FiltersState>
        get() = _state

    fun resetFiltersState() {
        _state.value = FiltersState(
            Filter(
                EMPTY_PARAM_VALUE,
                EMPTY_PARAM_VALUE,
                EMPTY_PARAM_VALUE,
                false
            )
        )
    }

    fun setFilters(filter: Filter?) {
        if (filter != null) {
            setFiltersUseCase.execute(filter)
        }
    }
}
