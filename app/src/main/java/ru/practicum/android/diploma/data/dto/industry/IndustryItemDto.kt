package ru.practicum.android.diploma.data.dto.industry

import com.google.gson.annotations.SerializedName

data class IndustryItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("industries")
    val industries: List<IndustryDto>,
    @SerializedName("name")
    val name: String
)
