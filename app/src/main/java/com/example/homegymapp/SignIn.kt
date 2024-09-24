// ActivitySignIn.kt
package com.example.homegymapp

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.homegymapp.databinding.ActivitySignInBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    lateinit var userdata: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userdata = UserDatabase.getDatabase(this)

        binding.signInButton.setOnClickListener {
            val name = binding.name.editText?.text.toString()
            val username = binding.userName.editText?.text.toString()
            val password = binding.password.editText?.text.toString()


            if (validateInput(name, username, password)) {
                val user = UserData(id = 0, name = name, userName = username, password = password)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        userdata.userDao().insertUser(user)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignIn, "User registered successfully", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignIn, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun validateInput(name: String, username: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showToast("Name cannot be empty")
                false
            }
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
