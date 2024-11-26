package ru.practicum.android.diploma.domain.models

data class Filter(
    val placeWork: String?,
    val industry: String?,
    var salary: String?,
    var withoutSalaryButton: Boolean
)

