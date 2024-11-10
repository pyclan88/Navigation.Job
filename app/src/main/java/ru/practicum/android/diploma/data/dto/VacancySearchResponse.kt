package ru.practicum.android.diploma.data.dto

class VacancySearchResponse(
    val expression: String,
    val results: List<VacancyDto>
):Response()
