package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class BrandingDto(
    @SerializedName("tariff")
    val tariff: Any,
    @SerializedName("type")
    val type: String
)
