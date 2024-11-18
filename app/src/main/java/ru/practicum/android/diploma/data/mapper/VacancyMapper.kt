package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.VacanciesSearchResponse
import ru.practicum.android.diploma.data.dto.vacancy.self.VacancyDto
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancySearchResult

class VacancyMapper {

    fun map(response: VacanciesSearchResponse) = VacancySearchResult(
        items = response.items.map { vacancy -> map(vacancy) },
        pages = response.pages
    )

    private fun map(dto: VacancyDto) = Vacancy(
        id = dto.id,
        imageUrl = dto.employer.logoUrls?.original,
        name = dto.name,
        city = getValueOrDefault(dto.area?.name, EMPTY_PARAM_VALUE),
        salaryFrom = getValueOrDefault(dto.salary?.from?.toString(), EMPTY_PARAM_VALUE),
        salaryTo = getValueOrDefault(dto.salary?.to?.toString(), EMPTY_PARAM_VALUE),
        currency = getValueOrDefault(dto.salary?.currency, EMPTY_PARAM_VALUE),
        employerName = dto.employer.name ?: EMPTY_PARAM_VALUE,
        experience = getValueOrDefault(dto.experience?.name, EMPTY_PARAM_VALUE),
        employmentName = getValueOrDefault(dto.employment?.name, EMPTY_PARAM_VALUE),
        schedule = getValueOrDefault(dto.schedule?.name, EMPTY_PARAM_VALUE)
    )

    private fun <T> getValueOrDefault(
        value: T?,
        defaultValue: String
    ): String = value?.toString() ?: defaultValue

    companion object {
        const val EMPTY_PARAM_VALUE = "Не указано"
    }
}
