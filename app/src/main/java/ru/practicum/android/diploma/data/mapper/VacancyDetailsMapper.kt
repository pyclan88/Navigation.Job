package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.common.AppConstants.EMPTY_INT_PARAM_VALUE
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.data.dto.vacancy.details.VacancyDetailsDto
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.util.AddressFormatter
import ru.practicum.android.diploma.util.SkillsFormatter

class VacancyDetailsMapper {
    private val addressFormatter = AddressFormatter()
    private val skillsFormatter = SkillsFormatter()

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
        descriptionSkills = skillsFormatter.skillsFormat(dto.keySkills),
        url = dto.alternateUrl ?: EMPTY_PARAM_VALUE,
        address = addressFormatter.addressFormat(dto.address)
    )
}
