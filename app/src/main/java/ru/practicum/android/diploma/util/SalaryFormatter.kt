package ru.practicum.android.diploma.util

import android.content.Context
import ru.practicum.android.diploma.R
import java.text.DecimalFormat

class SalaryFormatter(private val context: Context) {
    private val formatter = DecimalFormat("#,###")

    fun salaryFormat(salaryTo: Int, salaryFrom: Int, currency: String): String {
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
            salaryTo != 0 && salaryFrom == 0 -> context.getString(
                R.string.salary_to_format,
                formattedSalaryTo,
                currencySymbol
            )

            salaryTo == 0 && salaryFrom != 0 -> context.getString(
                R.string.salary_from_format,
                formattedSalaryFrom,
                currencySymbol
            )

            salaryTo != 0 && salaryFrom != 0 -> context.getString(
                R.string.salary_range_format,
                formattedSalaryFrom,
                currencySymbol,
                formattedSalaryTo,
                currencySymbol
            )

            else -> context.getString(R.string.salary_is_not_specified)
        }
    }
}
