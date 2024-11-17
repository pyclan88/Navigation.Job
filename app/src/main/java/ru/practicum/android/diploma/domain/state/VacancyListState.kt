package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Vacancy

sealed interface VacancyListState {
    data object Empty : VacancyListState
    data object Error : VacancyListState
    data class Data(val vacancies: List<Vacancy>) : VacancyListState
}

