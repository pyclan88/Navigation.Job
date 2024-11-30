package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Region

sealed interface LocationState {
    data class Data(val country: Country?, val region: Region?) : LocationState
    data object Empty : LocationState
}


