package ru.practicum.android.diploma.domain.api

interface FilterRepository {
    fun setFilters(value: List<String>)
    fun getFilters(): List<String>
}
