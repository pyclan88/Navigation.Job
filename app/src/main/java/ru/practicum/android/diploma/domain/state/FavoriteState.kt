package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Vacancy

data class FavoriteState(
    val vacancyList: VacancyList
) {

    sealed interface VacancyList {
        data object Empty : VacancyList
        data object Error : VacancyList
        data class Data(val vacancies: List<Vacancy>) : VacancyList
    }
}
