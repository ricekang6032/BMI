//package com.example.randomnumber
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class BMIActivity : AppCompatActivity() {
//    lateinit var bmiDisplay: RecyclerView
//    lateinit var backButton: Button // 返回按鈕
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_bmi)
//        backButton = findViewById(R.id.back_button)
//        bmiDisplay = findViewById(R.id.recyclerview)
//
//        val records = List_SharedPreferences(this).loadData()
//
//        bmiDisplay.layoutManager = LinearLayoutManager(this)
//        bmiDisplay.adapter = RecyclerView_Adapter(records)
//
//
//        // 設置返回按鈕的點擊事件
//        backButton.setOnClickListener {
//            // 使用 Intent 回到 MainActivity
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//    }
//}
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
    lateinit var bmiDisplay: RecyclerView
    lateinit var backButton: Button
    lateinit var deleteButton: Button // 刪除按鈕
    private lateinit var adapter: RecyclerView_Adapter
    private lateinit var records: MutableList<RecyclerView_Data> // 使用 MutableList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)

        backButton = findViewById(R.id.back_button)
        deleteButton = findViewById(R.id.delete_button)
        bmiDisplay = findViewById(R.id.recyclerview)

        // 加載數據
        val sharedPreferences = List_SharedPreferences(this)
        records = sharedPreferences.loadData().toMutableList()

        // 設置 RecyclerView 和 Adapter
        bmiDisplay.layoutManager = LinearLayoutManager(this)
        bmiDisplay.adapter = RecyclerView_Adapter(records)

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
                adapter.notifyItemRemoved(0) // 通知 RecyclerView 更新
                Toast.makeText(this, "刪除成功！", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "無資料可刪除", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
