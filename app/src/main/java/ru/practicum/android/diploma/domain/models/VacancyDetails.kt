package ru.practicum.android.diploma.domain.models

data class VacancyDetails(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val salaryFrom: String,
    val salaryTo: String,
    val currency: String,
    val employerName: String,
    val experience: String,
    val employmentName: String,
    val schedule: String,
    val descriptionResponsibility: String,
    val descriptionSkills: String
)
