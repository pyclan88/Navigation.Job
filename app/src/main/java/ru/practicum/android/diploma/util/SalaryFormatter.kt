package ru.practicum.android.diploma.util

import android.content.res.Resources
import ru.practicum.android.diploma.R
import java.text.DecimalFormat

object SalaryFormatter {
    private val formatter = DecimalFormat("#,###")

    fun salaryFormat(salaryTo: Int, salaryFrom: Int, currency: String, resources: Resources): String {
        val formattedSalaryTo = formatter.format(salaryTo).replace(",", " ")
        val formattedSalaryFrom = formatter.format(salaryFrom).replace(",", " ")
        val currencySymbol = when (currency) {
            "RUR" -> "₽"
            "USD" -> "$"
            "EUR" -> "€"
            "GBP" -> "£"
            else -> currency
        }

        return when {
            salaryTo != 0 && salaryFrom == 0 -> "до $formattedSalaryTo $currencySymbol"
            salaryTo == 0 && salaryFrom != 0 -> "от $formattedSalaryFrom $currencySymbol"
            salaryTo != 0 && salaryFrom != 0 -> "от $formattedSalaryFrom $currencySymbol до $formattedSalaryTo $currencySymbol"
            else -> resources.getString(R.string.salary_is_not_specified)
        }
    }
}
