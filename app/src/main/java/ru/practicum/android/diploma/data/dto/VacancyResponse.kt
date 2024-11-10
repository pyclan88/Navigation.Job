package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.dto.vacancy_dto.Item

data class VacancyResponse(
    val alternate_url: String,
    val arguments: Any,
    val clusters: Any,
    val fixes: Any,
    val found: Int,
    val items: List<Item>,
    val page: Int,
    val pages: Int,
    val per_page: Int,
    val suggests: Any
): Response()
