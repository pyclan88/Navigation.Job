package ru.practicum.android.diploma.domain.state

data class FiltersState    (
    val placeWork: String?,
    val industry: String?,
    val salary: String?,
    val withoutSalaryButton: Boolean
)
