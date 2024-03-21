package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoggedActivity : AppCompatActivity() {

    lateinit var welcomeText : TextView
    lateinit var usernameEdit : EditText
    lateinit var passwordEdit : EditText
    lateinit var editButton : Button
    lateinit var logoutButton : Button
    lateinit var app : App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        app = application as App

        welcomeText = findViewById(R.id.welcomeText)
        usernameEdit = findViewById(R.id.usernameEditInput)
        passwordEdit = findViewById(R.id.passwordEditInput)
        editButton = findViewById(R.id.editButton)
        logoutButton = findViewById(R.id.logoutButton)

        welcomeText.text = app.session.getUsername()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //pass
            }

            override fun afterTextChanged(s: Editable?) {
                editButton.isEnabled = usernameEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()
            }
        }

        usernameEdit.addTextChangedListener(textWatcher)
        passwordEdit.addTextChangedListener(textWatcher)

        editButton.setOnClickListener(){
            app.session.update(usernameEdit.text.toString(), passwordEdit.text.toString())
            welcomeText.text = app.session.getUsername()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Success")
            builder.setMessage("The username and password have been updated")
            builder.setPositiveButton("OK", null)
            builder.show()
        }

        logoutButton.setOnClickListener(){
            finish()
        }
    }
}