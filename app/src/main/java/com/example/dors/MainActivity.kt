package com.example.dors

import SQL
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var label: TextView
    private lateinit var userLog: EditText
    private lateinit var userPas: EditText
    private lateinit var button: Button
    private val sql = SQL()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        label = findViewById(R.id.lable) //
        userLog = findViewById(R.id.user_log)
        userPas = findViewById(R.id.user_pas)
        button = findViewById(R.id.enter_bt)

        button.setOnClickListener {
            val login = userLog.text.toString().trim()
            val password = userPas.text.toString().trim()
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        sql.connect()
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, "Подключение успешно", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, "Ошибка подключения: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    } finally {
                        sql.close()
                    }
                }
            }
        }
    }
}
