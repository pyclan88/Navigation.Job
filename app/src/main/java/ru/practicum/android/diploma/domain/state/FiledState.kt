package ru.practicum.android.diploma.domain.state

data class FieldState(
    val upperField: Field,
    val lowerField: Field,
) {

    sealed interface Field {
        data object Empty : Field
        data class Filled(val data: String) : Field
    }
}
