package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.ui.country.CountryViewModel
import ru.practicum.android.diploma.ui.favorite.FavoriteViewModel
import ru.practicum.android.diploma.ui.filters.FiltersViewModel
import ru.practicum.android.diploma.ui.industry.IndustryViewModel
import ru.practicum.android.diploma.ui.location.LocationViewModel
import ru.practicum.android.diploma.ui.search.SearchViewModel
import ru.practicum.android.diploma.ui.vacancy.VacancyViewModel

val viewModelModule = module {

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        VacancyViewModel(
            getVacancyDetailsUseCase = get(),
            getFavoriteVacancyByIdUseCase = get(),
            addVacancyToFavoriteUseCase = get(),
            deleteVacancyFromFavoriteUseCase = get(),
            sharingInteract = get()
        )
    }

    viewModel {
        FavoriteViewModel(get())
    }

    viewModel {
        LocationViewModel()
    }

    viewModel {
        FiltersViewModel(get(), get())
    }

    viewModel {
        CountryViewModel(get())
    }

    viewModel {
        IndustryViewModel(get())
    }
}
