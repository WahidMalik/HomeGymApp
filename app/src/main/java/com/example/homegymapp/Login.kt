package com.example.homegymapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.homegymapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userdata: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userdata = UserDatabase.getDatabase(this)

        binding.login.setOnClickListener {
            val username = binding.userName.editText?.text.toString()
            val password = binding.password.editText?.text.toString()


            if (validateInput(username, password)) {
                loginUser(username, password)
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(username) -> {
                showToast("Username cannot be empty")
                false
            }
            TextUtils.isEmpty(password) -> {
                showToast("Password cannot be empty")
                false
            }
            else -> true
        }
    }

    private fun loginUser(username: String, password: String) {
        lifecycleScope.launch {

            val user = userdata.userDao().getUser(username, password)
            if (user != null) {
                showToast("Login successful")
                startActivity(Intent(this@Login, HomePage::class.java))
                finish()
            } else {
                showToast("Login failed: Invalid credentials")
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
