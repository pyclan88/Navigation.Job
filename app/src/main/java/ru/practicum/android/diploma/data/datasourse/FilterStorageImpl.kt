package ru.practicum.android.diploma.data.datasourse

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val KEY_FILTERS = "items_filter"

class FilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : FilterStorage {
    override var filtersList: List<String>
        get() {
            val json: String? = sharedPreferences.getString(KEY_FILTERS, null)
            return if (json != null) {
                gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
            } else {
                emptyList()
            }
        }
        set(value) {
            val json = gson.toJson(value)
            sharedPreferences.edit()
                .putString(KEY_FILTERS, json)
                .apply()
        }
}
