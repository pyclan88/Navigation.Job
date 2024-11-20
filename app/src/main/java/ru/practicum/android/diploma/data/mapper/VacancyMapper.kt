package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.common.AppConstants.EMPTY_INT_PARAM_VALUE
import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.dto.vacancy.self.VacancyDto
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancySearchResult

class VacancyMapper {

    fun map(response: VacanciesSearchResponse) = VacancySearchResult(
        items = response.items.map { vacancy -> map(vacancy) },
        pages = response.pages,
        found = response.found,
    )

    private fun map(dto: VacancyDto) = Vacancy(
        id = dto.id,
        imageUrl = dto.employer.logoUrls?.original,
        name = dto.name,
        city = getValueOrDefault(dto.area?.name),
        salaryFrom = dto.salary?.from ?: EMPTY_INT_PARAM_VALUE,
        salaryTo = dto.salary?.to ?: EMPTY_INT_PARAM_VALUE,
        currency = getValueOrDefault(dto.salary?.currency),
        employerName = dto.employer.name ?: EMPTY_PARAM_VALUE,
        experience = getValueOrDefault(dto.experience?.name),
        employmentName = getValueOrDefault(dto.employment?.name),
        schedule = getValueOrDefault(dto.schedule?.name)
    )

    private fun <T> getValueOrDefault(
        value: T?,
    ): String = value?.toString() ?: EMPTY_PARAM_VALUE

    companion object {
        const val EMPTY_PARAM_VALUE = "Не указано"
    }
}
