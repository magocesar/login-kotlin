package com.example.myapplication

import android.app.Application

class App : Application(){

    lateinit var session: Session

    override fun onCreate() {
        super.onCreate()
        session = Session("user", "1234")
    }

}