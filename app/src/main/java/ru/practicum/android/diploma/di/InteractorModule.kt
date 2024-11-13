package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.usecase.GetVacanciesUseCase

val interactorModule = module {

    single {
        GetVacanciesUseCase(get())
    }

}
