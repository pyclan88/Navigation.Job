package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Industry

sealed interface IndustryState {
    data object Loading: IndustryState
    data object Empty : IndustryState
    data object Error : IndustryState
    data class Data(val industries: List<Industry>) : IndustryState
}
