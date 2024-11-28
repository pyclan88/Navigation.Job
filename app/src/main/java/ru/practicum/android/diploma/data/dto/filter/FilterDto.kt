package ru.practicum.android.diploma.data.dto.filter

import ru.practicum.android.diploma.data.dto.area.AreaDto
import ru.practicum.android.diploma.data.dto.area.SubAreaDto
import ru.practicum.android.diploma.data.dto.industry.IndustryDto

data class FilterDto(
    val area: AreaDto?,
    val subArea: SubAreaDto?,
    val industry: IndustryDto?,
    val salary: Int?,
    val withoutSalaryButton: Boolean
) {

    companion object {
        val empty = FilterDto(
            area = null,
            subArea = null,
            industry = null,
            salary = null,
            withoutSalaryButton = false
        )
    }
}
