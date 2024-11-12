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
                city = vacancy.area.name,
                salaryFrom = vacancy.salary.from.toString(),
                salaryTo = vacancy.salary.to.toString(),
                currency = vacancy.salary.currency,
                employerName = vacancy.employer.name,
                experience = vacancy.experience.name,
                employmentName = vacancy.employment.name,
                schedule = vacancy.schedule.name,
                descriptionResponsibility = vacancy.snippet.responsibility,
                descriptionRequirement = vacancy.snippet.requirement,
                descriptionConditions = "???",
                descriptionSkills = "???"
            )

        }
    }
}
