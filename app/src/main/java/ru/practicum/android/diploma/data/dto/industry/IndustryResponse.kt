package ru.practicum.android.diploma.data.dto.industry

import ru.practicum.android.diploma.data.dto.Response

data class IndustryResponse(
    val industriesList : List<IndustryItemDto>
) : Response()
