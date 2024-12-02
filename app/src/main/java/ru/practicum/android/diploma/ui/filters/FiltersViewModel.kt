package ru.practicum.android.diploma.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.models.Location
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.domain.state.FiltersState.Data.Empty
import ru.practicum.android.diploma.domain.state.FiltersState.Data.Payload
import ru.practicum.android.diploma.domain.state.FiltersState.Editor.Changed
import ru.practicum.android.diploma.domain.state.FiltersState.Editor.Unchanged
import ru.practicum.android.diploma.domain.usecase.filters.search.ClearSearchFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.search.GetSearchFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.search.SetSearchFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.tmp.ClearTmpFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.tmp.GetTmpFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.tmp.SetTmpFiltersUseCase

class FiltersViewModel(
    private val setTmpFiltersUseCase: SetTmpFiltersUseCase,
    private val getTmpFiltersUseCase: GetTmpFiltersUseCase,
    private val clearSearchFiltersUseCase: ClearSearchFiltersUseCase,
    private val clearTmpFiltersUseCase: ClearTmpFiltersUseCase,
    private val getSearchFiltersUseCase: GetSearchFiltersUseCase,
    private val setSearchFiltersUseCase: SetSearchFiltersUseCase
) : ViewModel() {

    private var searchFilters: Filter = getSearchFiltersUseCase.execute()
    private var isTmpFiltersInitialized = false

    private val _state: MutableStateFlow<FiltersState> =
        MutableStateFlow(FiltersState(editor = Unchanged, data = Empty))
    val state: StateFlow<FiltersState>
        get() = _state

    fun getFilters() = viewModelScope.launch(Dispatchers.Main) {
        if (!isTmpFiltersInitialized) {
            setTmpFiltersUseCase.execute(searchFilters)
            isTmpFiltersInitialized = true
        }

        val data: FiltersState.Data = when (val tmpFilters = getTmpFiltersUseCase.execute()) {
            Filter.empty -> Empty
            else -> Payload(tmpFilters)
        }
        // можно переписать оставить FilterState только с data
        val editorState = if (compareTmpAndSearchFilters()) {
            Unchanged
        } else {
            Changed
        }

        _state.value = FiltersState(editorState, data)
    }

    fun clearLocation() = setTmpFilters { copy(location = Location.empty) }
    fun clearIndustry() = setTmpFilters { copy(industry = null) }
    fun setSalary(salary: String) = setTmpFilters { copy(salary = salary.toIntOrNull()) }
    fun setWithoutSalaryButton(isEnabled: Boolean) = setTmpFilters { copy(withoutSalaryButton = isEnabled) }

    fun saveFilters() {
        val tmpFilters = getTmpFiltersUseCase.execute()
        setSearchFiltersUseCase.execute(tmpFilters)
    }

    fun clearFilters() {
        clearSearchFiltersUseCase.execute()
        clearTmpFiltersUseCase.execute()
        getFilters()
    }

    private fun setTmpFilters(lambda: Filter.() -> Filter) {
        val filter = getTmpFiltersUseCase.execute().lambda()
        setTmpFiltersUseCase.execute(filter)
        getFilters()
    }

    private fun compareTmpAndSearchFilters(): Boolean {
        val tmpFilters = getTmpFiltersUseCase.execute()
        val searchFilters = getSearchFiltersUseCase.execute()
        return tmpFilters == searchFilters
    }
}
