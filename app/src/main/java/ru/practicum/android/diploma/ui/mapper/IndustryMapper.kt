package ru.practicum.android.diploma.ui.mapper

import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.ui.adapters.industry.IndustryItem

class IndustryMapper {

    fun map(regions: List<Industry>, selectIndustry: Industry?): List<IndustryItem> =
        regions.map {
            IndustryItem(
                industry = Industry(it.id, it.name),
                isChecked = selectIndustry == it
            )
        }
}
