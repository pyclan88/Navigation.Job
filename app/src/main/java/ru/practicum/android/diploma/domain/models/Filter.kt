package ru.practicum.android.diploma.domain.models

data class Filter(
    val area: Country?,
    val region: Region?,
    val industry: Industry?,
    val salary: Int?,
    val withoutSalaryButton: Boolean
) {

    val location: String?
        get() {
            return if (area == null) {
                region?.name
            } else {
                region?.let { area.name + ", " + it.name } ?: area.name
            }
        }

    companion object {
        val empty = Filter(
            area = null,
            region = null,
            industry = null,
            salary = null,
            withoutSalaryButton = false
        )
    }
}
