package ru.practicum.android.diploma.data.dto.vacancy_dto

data class Salary(
    val currency: String,
    val from: Int,
    val gross: Boolean,
    val to: Int
)
