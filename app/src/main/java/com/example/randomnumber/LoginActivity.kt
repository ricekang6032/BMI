package com.example.randomnumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    // 預設帳號和密碼
    val Username = "1111"
    val Password = "2222"
    lateinit var accountEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // 找到 EditText 和 Button 元件
        accountEditText = findViewById(R.id.account_editText)
        passwordEditText = findViewById(R.id.password_editText)
        loginButton = findViewById(R.id.button)

        // 為登入按鈕添加點擊事件
        loginButton.setOnClickListener {
            val enteredUsername = accountEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            // 驗證帳號和密碼
            if (enteredUsername == Username && enteredPassword == Password) {
                // 顯示成功訊息
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                // 跳轉至 MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // 結束當前的 LoginActivity
            } else {
                // 顯示失敗訊息
                Toast.makeText(this, "帳號密碼錯誤，無法登入", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
