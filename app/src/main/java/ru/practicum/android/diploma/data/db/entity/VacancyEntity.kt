package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)
