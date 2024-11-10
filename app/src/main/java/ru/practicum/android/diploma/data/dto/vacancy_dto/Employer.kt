package ru.practicum.android.diploma.data.dto.vacancy_dto

data class Employer(
    val accredited_it_employer: Boolean,
    val alternate_url: String,
    val id: String,
    val logo_urls: LogoUrls,
    val name: String,
    val trusted: Boolean,
    val url: String,
    val vacancies_url: String
)
