package ru.practicum.android.diploma.data.dto.industry

import com.google.gson.annotations.SerializedName

data class IndustryDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
