package ru.practicum.android.diploma.domain.models

class Industry(
    val id: String,
    val name: String,
    var isSelected: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Industry

        if (id != other.id) return false
        if (name != other.name) return false
        if (isSelected != other.isSelected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + isSelected.hashCode()
        return result
    }
}
