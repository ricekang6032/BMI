package com.example.randomnumber

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class List_SharedPreferences (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("My prefs", Context.MODE_PRIVATE)

    fun saveData(data: Float){
        val editor = prefs.edit()
        // 取得已存在的記錄，將它們轉換成可變的列表
        val gson = Gson()
        val type = object : TypeToken<MutableList<RecyclerView_Data>>() {}.type
        val recordsJson = prefs.getString("records", null)
        val records: MutableList<RecyclerView_Data> = gson.fromJson(recordsJson, type) ?: mutableListOf()

        // 新增新的記錄
        val newRecord = RecyclerView_Data(id = records.size + 1, bmi = "%.2f".format(data))
        records.add(newRecord)

        // 將記錄轉換回 JSON 並保存
        editor.putString("records", gson.toJson(records))
        editor.apply()
    }

    fun loadData(): List<RecyclerView_Data>{
        val gson = Gson()
        val type = object : TypeToken<List<RecyclerView_Data>>() {}.type
        val recordsJson = prefs.getString("records", null)
        return gson.fromJson(recordsJson, type) ?: listOf()
    }
}