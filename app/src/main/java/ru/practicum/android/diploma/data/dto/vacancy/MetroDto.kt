package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class MetroDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("line_id")
    val lineId: String,
    @SerializedName("line_name")
    val lineName: String,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("station_id")
    val stationId: String,
    @SerializedName("station_name")
    val stationName: String
)
