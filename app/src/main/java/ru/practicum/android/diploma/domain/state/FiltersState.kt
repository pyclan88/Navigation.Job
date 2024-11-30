package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Filter

sealed interface FiltersState {
    data object Empty : FiltersState
    data class Data(val filters: Filter, val buttonApplyState: Boolean) : FiltersState
}
