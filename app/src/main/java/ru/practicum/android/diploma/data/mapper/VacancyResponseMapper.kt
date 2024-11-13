package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.VacancySearchResponse
import ru.practicum.android.diploma.data.dto.vacancy.ItemDto
import ru.practicum.android.diploma.domain.models.Vacancy

class VacancyResponseMapper {
    fun map(response: VacancySearchResponse): List<Vacancy> {
        return response.items.map { vacancy -> createVacancy(vacancy) }
    }

    fun createVacancy(vacancy: ItemDto): Vacancy {
        return Vacancy(
            id = vacancy.id,
            imageUrl = vacancy.employer.url,
            name = vacancy.name,
            city = getValueOrDefault(vacancy.area?.name, "Не указано"),
            salaryFrom = getValueOrDefault(vacancy.salary?.from?.toString(), "Не указано"),
            salaryTo = getValueOrDefault(vacancy.salary?.to?.toString(), "Не указано"),
            currency = getValueOrDefault(vacancy.salary?.currency, "Не указана"),
            employerName = vacancy.employer.name,
            experience = getValueOrDefault(vacancy.experience?.name, "Не указано"),
            employmentName = getValueOrDefault(vacancy.employment?.name, "Не указано"),
            schedule = getValueOrDefault(vacancy.schedule?.name, "Не указано"),
            descriptionResponsibility = getValueOrDefault(vacancy.snippet.responsibility, "Не указано"),
            descriptionRequirement = getValueOrDefault(vacancy.snippet.requirement, "Не указано"),
            descriptionConditions = "???", // Может быть заполнено позже
            descriptionSkills = "???" // Может быть заполнено позже
        )
    }

    fun <T> getValueOrDefault(value: T?, defaultValue: String): String {
        return value?.toString() ?: defaultValue
    }
}
