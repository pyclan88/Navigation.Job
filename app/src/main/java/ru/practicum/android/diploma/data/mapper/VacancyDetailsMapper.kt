package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.util.JsonParser

class VacancyDetailsMapper {
    private val jsonParser = JsonParser()
    fun map(dto: VacancyDetailsDto) = VacancyDetails(
        id = dto.id,
        imageUrl = dto.employer.logoUrls?.original,
        name = dto.name,
        city = getValueOrDefault(dto.area?.name, EMPTY_PARAM_VALUE),
        salaryFrom = getValueOrDefault(dto.salary?.from?.toString(), EMPTY_PARAM_VALUE),
        salaryTo = getValueOrDefault(dto.salary?.to?.toString(), EMPTY_PARAM_VALUE),
        currency = getValueOrDefault(dto.salary?.currency, EMPTY_PARAM_VALUE),
        employerName = dto.employer.name,
        experience = getValueOrDefault(dto.experience?.name, EMPTY_PARAM_VALUE),
        employmentName = getValueOrDefault(dto.employment?.name, EMPTY_PARAM_VALUE),
        schedule = getValueOrDefault(dto.schedule?.name, EMPTY_PARAM_VALUE),
        descriptionResponsibility = getValueOrDefault(dto.description, EMPTY_PARAM_VALUE),
        descriptionRequirement = getValueOrDefault(dto.description, EMPTY_PARAM_VALUE),
        descriptionConditions = getValueOrDefault(dto.description, EMPTY_PARAM_VALUE),
        descriptionSkills = getValueOrDefault(jsonParser.toJsonString(dto.keySkills), EMPTY_PARAM_VALUE),
    )

    private fun <T> getValueOrDefault(
        value: T?,
        defaultValue: String
    ): String = value?.toString() ?: defaultValue

    companion object {
        const val EMPTY_PARAM_VALUE = "Не указано"
    }
}
