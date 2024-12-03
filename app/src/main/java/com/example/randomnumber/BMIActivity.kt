package com.example.randomnumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BMIActivity : AppCompatActivity() {
    private lateinit var bmiDisplay: RecyclerView
    private lateinit var backButton: Button
    private lateinit var deleteButton: Button
    private lateinit var adapter: RecyclerView_Adapter
    private lateinit var records: MutableList<RecyclerView_Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)

        // 綁定 UI 元件
        backButton = findViewById(R.id.back_button)
        deleteButton = findViewById(R.id.delete_button)
        bmiDisplay = findViewById(R.id.recyclerview)

        // 加載數據
        val sharedPreferences = List_SharedPreferences(this)
        records = sharedPreferences.loadData().toMutableList()

        // 初始化 Adapter 並設置到 RecyclerView
        adapter = RecyclerView_Adapter(records)
        bmiDisplay.layoutManager = LinearLayoutManager(this)
        bmiDisplay.adapter = adapter

        // 返回按鈕的點擊事件
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 刪除按鈕的點擊事件
        deleteButton.setOnClickListener {
            if (records.isNotEmpty()) {
                records.removeAt(0) // 刪除第一筆資料
                sharedPreferences.saveAllData(records) // 更新 SharedPreferences
                adapter.notifyDataSetChanged() // 通知 RecyclerView 更新
                Toast.makeText(this, "刪除成功！", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "無資料可刪除", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
