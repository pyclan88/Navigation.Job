package ru.practicum.android.diploma.domain.models

data class Vacancy(
    val id: String,
    val imageUrl: String,
    val name: String,
    val city: String,
    val salaryFrom: String,
    val salaryTo: String,
    val currency: String,
    val employerName: String,
    val experience: String,
    val employmentName: String,
    val schedule: String,
    val descriptionResponsibility: String,
    val descriptionRequirement: String,
    val descriptionConditions: String,
    val descriptionSkills: String
)
