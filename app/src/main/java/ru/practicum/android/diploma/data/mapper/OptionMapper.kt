package ru.practicum.android.diploma.data.mapper

class OptionMapper {

    fun map(
        expression: String,
        page: String,
        area: String = "",
        salary: String = "0",
        industry: String = "",
        onlyWithSalary: String = "false"
    ): HashMap<String, String> {
        val options: HashMap<String, String> = HashMap()
        options["text"] = expression
        options["page"] = page
        options["per_page"] = COUNT_PER_PAGE
        options["only_with_salary"] = onlyWithSalary
        if (area.isNotBlank()) {
            options["area"] = area
        }
        if (salary == "0") {
            options["salary"] = salary
        }
        if (industry.isNotBlank()) {
            options["industry"] = industry
        }
        return options
    }

    companion object {
        const val COUNT_PER_PAGE = "20"
    }
}
