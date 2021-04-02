package com.test.cryptocourse.manager

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.cryptocourse.Constant
import com.test.cryptocourse.model.ModelCrypto
import java.lang.reflect.Type

/**
 * Created by
 */
class SharedPreferencesManager {

    private val gson = Gson()

    fun saveList(list: List<ModelCrypto>, context: Context){
        var preferences: SharedPreferences = context.getSharedPreferences(
            Constant.keySharedPreferences,
            Context.MODE_PRIVATE
        )!!
        val editor = preferences.edit()
        val json = gson.toJson(list)
        editor.putString(Constant.keySharedPreferences, json)
        editor.apply()
    }

    fun loadList(context: Context?): List<ModelCrypto>? {
        var preferences: SharedPreferences = context!!.getSharedPreferences(
            Constant.keySharedPreferences,
            Context.MODE_PRIVATE
        )!!
        val json = preferences.getString(Constant.keySharedPreferences, "")
        val type: Type = object : TypeToken<List<ModelCrypto?>?>() {}.type
        return gson.fromJson(json, type)
    }
}