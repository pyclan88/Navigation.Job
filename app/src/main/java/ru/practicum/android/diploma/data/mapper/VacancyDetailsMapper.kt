package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.common.AppConstants.EMPTY_INT_PARAM_VALUE
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.data.formatter.AddressFormatter
import ru.practicum.android.diploma.data.formatter.SkillsFormatter

class VacancyDetailsMapper {
    private val addressFormatter = AddressFormatter()
    private val skillsFormatter = SkillsFormatter()

    fun map(dto: VacancyDetailsDto) = VacancyDetails(
        id = dto.id,
        imageUrl = dto.employer?.logoUrls?.original,
        name = getValueOrDefault(dto.name),
        area = getValueOrDefault(dto.area.name),
        salaryFrom = dto.salary?.from ?: EMPTY_INT_PARAM_VALUE,
        salaryTo = dto.salary?.to ?: EMPTY_INT_PARAM_VALUE,
        currency = getValueOrDefault(dto.salary?.currency),
        employerName = getValueOrDefault(dto.employer?.name),
        experience = getValueOrDefault(dto.experience?.name),
        employmentName = getValueOrDefault(dto.employment?.name),
        schedule = getValueOrDefault(dto.schedule.name),
        description = getValueOrDefault(dto.description),
        descriptionSkills = skillsFormatter.skillsFormat(dto.keySkills),
        url = getValueOrDefault(dto.alternateUrl),
        address = addressFormatter.addressFormat(dto.address)
    )

    private fun <T> getValueOrDefault(
        value: T?
    ): String = value?.toString() ?: EMPTY_PARAM_VALUE

}
