package com.example.myapplication

class Session(private var username: String, private var password: String) {
    fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun isCredentialsValid(username: String, password: String): Boolean {
        return this.username == username && this.password == password
    }

    fun update(username: String, password: String) {
        this.username = username
        this.password = password
    }
}