package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.VacancyDetails

data class VacancyDetailsState(
    val details: Vacancy
) {

    sealed interface Vacancy {
        data object Loading : Vacancy
        data object Empty : Vacancy
        data object Error : Vacancy
        data class Data(val vacancy: VacancyDetails) : Vacancy
    }
}
