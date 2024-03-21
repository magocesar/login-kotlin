package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton : Button
    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.usernameInput)
        password = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        app = application as App

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Pass
            }

            override fun afterTextChanged(s: Editable?) {
                loginButton.isEnabled = username.text.isNotEmpty() && password.text.isNotEmpty()
            }
        }

        username.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener(){
            if(app.session.isCredentialsValid(username.text.toString(), password.text.toString())){
                val intent = Intent(this, LoggedActivity::class.java)
                startActivity(intent)
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Invalid Credentials")
                builder.setMessage("The username or password is incorrect")
                builder.setPositiveButton("OK", null)
                builder.show()
            }
        }






    }
}