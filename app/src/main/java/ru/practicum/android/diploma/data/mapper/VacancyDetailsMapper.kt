package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.util.JsonParser

class VacancyDetailsMapper() {
    private val jsonParser = JsonParser()
    fun map(dto: VacancyDetailsDto) = VacancyDetails(
        id = dto.id,
        name = dto.name,
        imageUrl = dto.alternateUrl,
        salaryFrom = dto.salary.from.toString(),
        salaryTo = dto.salary.to.toString(),
        currency = dto.salary.currency,
        employerName = dto.employer.name,
        experience = dto.experience.name,
        employmentName = dto.employment.name,
        schedule = dto.schedule.name,
        descriptionResponsibility = dto.description,
        descriptionSkills = jsonParser.toJsonString(dto.keySkills)
    )
}
