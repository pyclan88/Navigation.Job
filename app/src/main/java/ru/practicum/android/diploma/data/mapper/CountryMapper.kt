package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.area.AreaDto
import ru.practicum.android.diploma.data.dto.area.AreaResponse
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Region

class CountryMapper {

    fun map(dto: AreaResponse): List<Country> {
        return dto.area.map {
            convertAreaDtoToCountry(it)
        }
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
