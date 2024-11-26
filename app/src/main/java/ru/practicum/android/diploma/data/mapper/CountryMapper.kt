package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.country.CountryResponse
import ru.practicum.android.diploma.domain.models.Country

class CountryMapper {

    fun map(dto: CountryResponse): List<Country> =
        dto.countries.map { Country(id = it.id, name = it.name) }
}
