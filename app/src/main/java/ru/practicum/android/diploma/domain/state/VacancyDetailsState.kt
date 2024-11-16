package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.VacancyDetails

data class VacancyDetailsState(
    val detailsList: VacancyDetailsList
) {

    sealed interface VacancyDetailsList {
        data object Loading : VacancyDetailsList
        data object Empty : VacancyDetailsList
        data object Error : VacancyDetailsList
        data class Data(val details: VacancyDetails) : VacancyDetailsList
    }
}
