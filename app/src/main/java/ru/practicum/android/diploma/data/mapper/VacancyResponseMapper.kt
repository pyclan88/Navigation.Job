package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.VacancySearchResponse
import ru.practicum.android.diploma.domain.models.Vacancy

class VacancyResponseMapper {
    fun map(response: VacancySearchResponse): List<Vacancy> {
        return response.items.map { vacancy ->
            Vacancy(
                id = vacancy.id,
                imageUrl = vacancy.employer.url,
                name = vacancy.name,
                city = vacancy.area?.name ?: "Не указано", // Значение по умолчанию
                salaryFrom = vacancy.salary?.from?.toString() ?: "Не указано", // Значение по умолчанию
                salaryTo = vacancy.salary?.to?.toString() ?: "Не указано", // Значение по умолчанию
                currency = vacancy.salary?.currency ?: "Не указана", // Значение по умолчанию
                employerName = vacancy.employer.name,
                experience = vacancy.experience?.name ?: "Не указано", // Значение по умолчанию
                employmentName = vacancy.employment?.name ?: "Не указано", // Значение по умолчанию
                schedule = vacancy.schedule?.name ?: "Не указано", // Значение по умолчанию
                descriptionResponsibility = vacancy.snippet.responsibility ?: "Не указано", // Значение по умолчанию
                descriptionRequirement = vacancy.snippet.requirement ?: "Не указано", // Значение по умолчанию
                descriptionConditions = "???", // Может быть заполнено позже
                descriptionSkills = "???" // Может быть заполнено позже
            )

        }
    }
}
