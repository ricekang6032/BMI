package com.example.randomnumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BMIActivity : AppCompatActivity() {
    lateinit var bmiDisplay: RecyclerView
    lateinit var backButton: Button // 返回按鈕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)
        backButton = findViewById(R.id.back_button)
        bmiDisplay = findViewById(R.id.recyclerview)

        val records = List_SharedPreferences(this).loadData()

        bmiDisplay.layoutManager = LinearLayoutManager(this)
        bmiDisplay.adapter = RecyclerView_Adapter(records)


        // 設置返回按鈕的點擊事件
        backButton.setOnClickListener {
            // 使用 Intent 回到 MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}