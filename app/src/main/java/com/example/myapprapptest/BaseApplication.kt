package com.example.myapprapptest

import android.app.Application
import com.example.myapprapptest.di.AppComponent
import com.example.myapprapptest.di.AppModule
import com.example.myapprapptest.di.DaggerAppComponent

class BaseApplication : Application(){

    companion object{
        private lateinit var appComponent:AppComponent

        fun getAppComponent(): AppComponent{
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}