package ru.practicum.android.diploma.util

import ru.practicum.android.diploma.data.dto.vacancy.nested.KeySkillDto
import kotlin.text.Typography.middleDot

class SkillsFormatter {
    fun skillsFormat(list: List<KeySkillDto>): String {
        return list.mapIndexed { _, keySkillDto -> "$middleDot  ${keySkillDto.name}" }.joinToString("\n")
    }
}
