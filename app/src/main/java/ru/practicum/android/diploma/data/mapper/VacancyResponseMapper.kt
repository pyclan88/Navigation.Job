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
            city = getValueOrDefault(vacancy.area?.name, empty_param),
            salaryFrom = getValueOrDefault(vacancy.salary?.from?.toString(), empty_param),
            salaryTo = getValueOrDefault(vacancy.salary?.to?.toString(), empty_param),
            currency = getValueOrDefault(vacancy.salary?.currency, empty_param),
            employerName = vacancy.employer.name,
            experience = getValueOrDefault(vacancy.experience?.name, empty_param),
            employmentName = getValueOrDefault(vacancy.employment?.name, empty_param),
            schedule = getValueOrDefault(vacancy.schedule?.name, empty_param),
            descriptionResponsibility = getValueOrDefault(vacancy.snippet.responsibility, empty_param),
            descriptionRequirement = getValueOrDefault(vacancy.snippet.requirement, empty_param),
            descriptionConditions = "???", // Может быть заполнено позже
            descriptionSkills = "???" // Может быть заполнено позже
        )
    }

    fun <T> getValueOrDefault(value: T?, defaultValue: String): String {
        return value?.toString() ?: defaultValue
    }

    companion object {
        const val empty_param = "Не указано"
    }
}
