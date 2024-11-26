package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.sharing.SharingInteract
import ru.practicum.android.diploma.domain.sharing.impl.SharingInteractImpl
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.domain.usecase.GetRegionUseCase
import ru.practicum.android.diploma.domain.usecase.vacancy.GetVacanciesUseCase
import ru.practicum.android.diploma.domain.usecase.vacancy.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.domain.usecase.favorite.AddVacancyToFavoriteUseCase
import ru.practicum.android.diploma.domain.usecase.favorite.DeleteVacancyFromFavoriteUseCase
import ru.practicum.android.diploma.domain.usecase.favorite.GetAllFavoriteVacanciesUseCase
import ru.practicum.android.diploma.domain.usecase.favorite.GetFavoriteVacancyByIdUseCase
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase

val useCaseModule = module {

    single {
        GetVacanciesUseCase(get())
    }

    single {
        GetVacancyDetailsUseCase(get())
    }

    single {
        AddVacancyToFavoriteUseCase(get())
    }

    single {
        DeleteVacancyFromFavoriteUseCase(get())
    }

    single {
        GetAllFavoriteVacanciesUseCase(get())
    }

    single {
        GetFavoriteVacancyByIdUseCase(get())
    }

    single<SharingInteract> {
        SharingInteractImpl(get())
    }

    single {
        SetFiltersUseCase(get())
    }

    single {
        GetFiltersUseCase(get())
    }

    single {
        GetIndustriesUseCase(get())
    }

    single {
        GetRegionUseCase(get())
    }
}
