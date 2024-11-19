package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.common.AppConstants.EMPTY_INT_PARAM_VALUE
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto
import ru.practicum.android.diploma.data.dto.vacancy.nested.AddressDto
import ru.practicum.android.diploma.data.dto.vacancy.nested.KeySkillDto
import ru.practicum.android.diploma.domain.models.VacancyDetails
import kotlin.text.Typography.middleDot

class VacancyDetailsMapper {

    fun map(dto: VacancyDetailsDto) = VacancyDetails(
        id = dto.id,
        imageUrl = dto.employer?.logoUrls?.original,
        name = dto.name ?: EMPTY_PARAM_VALUE,
        area = dto.area.name ?: EMPTY_PARAM_VALUE,
        salaryFrom = dto.salary?.from ?: EMPTY_INT_PARAM_VALUE,
        salaryTo = dto.salary?.to ?: EMPTY_INT_PARAM_VALUE,
        currency = dto.salary?.currency ?: EMPTY_PARAM_VALUE,
        employerName = dto.employer?.name ?: EMPTY_PARAM_VALUE,
        experience = dto.experience?.name ?: EMPTY_PARAM_VALUE,
        employmentName = dto.employment?.name ?: EMPTY_PARAM_VALUE,
        schedule = dto.schedule.name ?: EMPTY_PARAM_VALUE,
        description = dto.description ?: EMPTY_PARAM_VALUE,
        descriptionSkills = skillsFormatter(dto.keySkills),
        url = dto.alternateUrl ?: EMPTY_PARAM_VALUE,
        address = addressFormatter(dto.address)
    )

    private fun skillsFormatter(list: List<KeySkillDto>): String {
        return list.mapIndexed { _, keySkillDto -> "$middleDot  ${keySkillDto.name}" }.joinToString("\n")
    }

    private fun addressFormatter(address: AddressDto?): String {
        return address?.let {
            when {
                it.city.isNullOrEmpty() -> EMPTY_PARAM_VALUE
                it.street.isNullOrEmpty() -> "${it.city}, ${it.building}"
                it.building.isNullOrEmpty() -> "${it.city}, ${it.street}"
                else -> "${it.city}, ${it.street}, ${it.building}"
            }
        } ?: EMPTY_PARAM_VALUE
    }
}

