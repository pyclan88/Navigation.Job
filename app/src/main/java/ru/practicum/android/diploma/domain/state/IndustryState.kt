package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Industry

data class IndustryState(
    val input: Input,
    val industries: IndustriesList,
) {

    sealed interface Input {
        data object Empty : Input
        data class Text(val value: String) : Input
    }

    sealed interface IndustriesList {
        data object NoInternet : IndustriesList
        data object Empty : IndustriesList
        data object Loading : IndustriesList
        data object Error : IndustriesList
        data class Data(val industries: List<Industry>) : IndustriesList
    }
}
