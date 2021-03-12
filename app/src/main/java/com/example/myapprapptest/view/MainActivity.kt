package com.example.myapprapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapprapptest.BaseApplication
import com.example.myapprapptest.R
import com.example.myapprapptest.viewmodel.ViewModelMovies

class MainActivity : AppCompatActivity() {

    lateinit var mViewModelMovies: ViewModelMovies

    var adapterTopMovies = AdapterTopMovies(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModelMovies = ViewModelProvider(this).get(ViewModelMovies::class.java)
        BaseApplication.getAppCcomponent().inject(mViewModelMovies)

        var recyclerViewTopMovies = findViewById<RecyclerView>(R.id.rv_adapter_top_movies)
        recyclerViewTopMovies.setHasFixedSize(true)
        recyclerViewTopMovies.adapter = adapterTopMovies
        recyclerViewTopMovies.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onResume() {
        super.onResume()

        mViewModelMovies.getData()
        mViewModelMovies.mListTop.observe(this, { listTopMovies ->
            adapterTopMovies.updateList(listTopMovies)
        })
    }
}