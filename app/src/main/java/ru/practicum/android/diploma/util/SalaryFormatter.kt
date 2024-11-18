package ru.practicum.android.diploma.util

import android.content.res.Resources
import ru.practicum.android.diploma.R

class SalaryFormatter {
    fun salaryFormat(salaryTo: Int, salaryFrom: Int, currency: String, resources: Resources): String {
        val salary =
            if (salaryTo != 0 && salaryFrom == 0) {
                "до $salaryTo $currency"
            } else if (salaryTo == 0 && salaryFrom != 0) {
                "от $salaryFrom $currency"
            } else if (salaryTo != 0 && salaryFrom != 0) {
                "от $salaryFrom $currency до $salaryTo $currency"
            } else {
                resources.getString(R.string.salary_is_not_specified)
            }
        return salary
    }
}
