package ru.practicum.android.diploma.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonParser {
    private val gson = Gson()

    fun toJsonString(obj: Any): String {
        return gson.toJson(obj)
    }

    fun <T> getObject(jsonString: String, typeToken: TypeToken<T>): T? {
        return try {
            gson.fromJson(jsonString, typeToken.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
