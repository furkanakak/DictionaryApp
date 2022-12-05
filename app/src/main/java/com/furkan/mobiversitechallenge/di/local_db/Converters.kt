package com.furkan.mobiversitechallenge.di.local_db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listToString(list: ArrayList<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(value: String): ArrayList<String> {
        return Gson().fromJson(value, object : TypeToken<ArrayList<String>>() {}.type)
    }

}