package ru.practicum.android.diploma.domain.models

data class Filter(
    val placeWork: String?,
    val industry: String?,
    val salary: String?,
    val withoutSalaryButton: Boolean
)
