package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.repository.CountryRepositoryImpl
import ru.practicum.android.diploma.data.repository.FavoriteVacanciesRepositoryImpl
import ru.practicum.android.diploma.data.repository.IndustryRepositoryImpl
import ru.practicum.android.diploma.data.repository.VacancyRepositoryImpl
import ru.practicum.android.diploma.domain.api.CountryRepository
import ru.practicum.android.diploma.domain.api.FavoriteVacanciesRepository
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.api.VacancyRepository

val repositoryModule = module {

    single<VacancyRepository> {
        VacancyRepositoryImpl(
            vacancyNetworkClient = get(),
            detailsNetworkClient = get(),
            vacancyMapper = get(),
            vacancyDetailsMapper = get(),
            optionMapper = get(),
        )
    }

    single<FavoriteVacanciesRepository> {
        FavoriteVacanciesRepositoryImpl(
            favoriteDao = get<AppDatabase>().favoriteVacanciesDao(),
            convertor = get()
        )
    }

    single<IndustryRepository> {
        IndustryRepositoryImpl(
            industryNetworkClient = get(),
            industryMapper = get()
        )
    }

    single<CountryRepository> {
        CountryRepositoryImpl(
            countryNetworkClient = get(),
            countryMapper = get()
        )
    }
}
