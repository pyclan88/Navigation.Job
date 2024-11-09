package ru.practicum.android.diploma.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class JsonParser {
    private val gson = Gson()

    fun toJsonString(obj: Any): String {
        return gson.toJson(obj)
    }

    fun <T> getObject(jsonString: String, typeToken: TypeToken<T>): T? {
        return try {
            gson.fromJson(jsonString, typeToken.type)
        } catch (e: JsonIOException) {
            Log.e("JsonParserError", "Ошибка ввода-вывода JSON: ${e.message}")
            null
        } catch (e: IllegalStateException) {
            Log.e("JsonParserError", "Некорректное состояние при парсинге JSON: ${e.message}")
            null
        } catch (e: JsonSyntaxException) {
            Log.e("JsonParserError", "Ошибка синтаксиса JSON: ${e.message}")
            null
        }

    }
}
