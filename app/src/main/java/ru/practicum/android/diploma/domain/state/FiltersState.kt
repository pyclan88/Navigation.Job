package ru.practicum.android.diploma.domain.state

import ru.practicum.android.diploma.domain.models.Filter

data class FiltersState(
    // поле editor после изменений не используется
    val editor: Editor,
    val data: Data
) {

    sealed interface Editor {
        data object Changed : Editor
        data object Unchanged : Editor
    }

    sealed interface Data {
        data object Empty : Data
        data class Payload(val filters: Filter) : Data
    }
}
