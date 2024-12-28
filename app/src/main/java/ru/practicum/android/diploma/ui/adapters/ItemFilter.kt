package ru.practicum.android.diploma.ui.adapters

import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Region

data class ItemFilter(
    val data: Any,
    val type: Int
) {

    fun getItemId(): Int {
        return when (data) {
            is Region -> data.id.toInt()
            is Country -> data.id.toInt()
            else -> {
                return 0
            }
        }
    }

    companion object {
        const val TYPE_AREA = 0
        const val TYPE_REGION = 1
    }
}
