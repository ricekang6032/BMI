package com.example.randomnumber

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 宣告全域變數方便之後使用
    lateinit var displayTextView: TextView
    lateinit var enterButton: Button
    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText
    val number: Int = 0
    lateinit var showBmiButton: Button  // 新增的按鈕

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 指定個變數對應元件 id
        displayTextView = findViewById(R.id.display_textView)
        enterButton = findViewById(R.id.enter_button)
        heightEditText = findViewById(R.id.height_editText)
        weightEditText = findViewById(R.id.weight_editText)
        showBmiButton = findViewById(R.id.change_button)  // 找到新按鈕的 ID

        // 設置 button 的點擊事件
        enterButton.setOnClickListener {
            val height = heightEditText.text.toString().toFloat() / 100
            val weight = weightEditText.text.toString().toFloat()
            val bmi = weight / height / height
            val warning: String = when(bmi){
                in 0f..18.5f -> "體重過輕"
                in 18.5f..24f -> "健康體位"
                in 24f..27f -> "體重過重"
                else -> "肥胖"
            }
            displayTextView.text = "Your BMI: $bmi\n$warning"
            List_SharedPreferences(this).saveData(bmi)
            Toast.makeText(this, "以儲存BMI資料", Toast.LENGTH_SHORT).show()
        }

        // 讀取 BMI 值並傳遞到下一個 Activity
        showBmiButton.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }
    }
}