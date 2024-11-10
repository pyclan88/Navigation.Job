package ru.practicum.android.diploma.data.dto.vacancy_dto

data class Address(
    val building: String,
    val city: String,
    val description: Any,
    val id: String,
    val lat: Double,
    val lng: Double,
    val metro: Metro,
    val metro_stations: List<MetroStation>,
    val raw: String,
    val street: String
)
