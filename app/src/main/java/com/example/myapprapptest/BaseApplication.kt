package com.example.myapprapptest

import android.app.Application
import com.example.myapprapptest.di.AppComponent
import com.example.myapprapptest.di.DaggerAppComponent

class BaseApplication : Application(){

    companion object{
        private lateinit var appComponent:AppComponent

        fun getAppCcomponent(): AppComponent{
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

    }
}