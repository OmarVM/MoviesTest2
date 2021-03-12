package com.example.myapprapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapprapptest.BaseApplication
import com.example.myapprapptest.R
import com.example.myapprapptest.viewmodel.ViewModelMovies

class MainActivity : AppCompatActivity() {

    lateinit var mViewModelMovies: ViewModelMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModelMovies = ViewModelProvider(this).get(ViewModelMovies::class.java)
        BaseApplication.getAppCcomponent().inject(mViewModelMovies)
    }

    override fun onResume() {
        super.onResume()

        mViewModelMovies.getData()
    }
}