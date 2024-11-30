package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.area.AreaDto
import ru.practicum.android.diploma.data.dto.area.AreaResponse
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Region

class CountryMapper {

    companion object {
        private const val OTHER_COUNTRY_ITEM_ID = "1001"
    }

    fun map(dto: AreaResponse): List<Country> {
        val countries = dto.area.map { convertAreaDtoToCountry(it) }.toMutableList()

        countries.find { it.id == OTHER_COUNTRY_ITEM_ID }?.let {
            countries.remove(it)
            countries.add(it)
        }

        return countries
    }

    private fun convertAreaDtoToCountry(dto: AreaDto): Country {
        return Country(
            id = dto.id,
            name = dto.name,
            regions = dto.areas.map {
                Region(id = it.id, name = it.name)
            }
        )
    }
}
