package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Region

data class RegionState(
    val input: Input,
    val regions: Regions
) {

    sealed interface Input {
        data object Empty : Input
        data class Text(val value: String) : Input
    }

    sealed interface Regions {
        data object Empty : Regions
        data object Loading : Regions
        data object Error : Regions
        data class Data(val regions: List<Region>) : Regions
    }
}
