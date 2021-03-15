package com.example.myapprapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapprapptest.BaseApplication
import com.example.myapprapptest.R
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.viewmodel.ViewModelMovies
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IOnClickListener {

    lateinit var mViewModelMovies: ViewModelMovies

    @Inject
    lateinit var adapterTopMovies: AdapterMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModelMovies = ViewModelProvider(this).get(ViewModelMovies::class.java)
        BaseApplication.getAppComponent().inject(mViewModelMovies)
        BaseApplication.getAppComponent().inject(this)

        adapterTopMovies.setClickListener(this)
        var recyclerViewTopMovies = findViewById<RecyclerView>(R.id.rv_adapter_top_movies)
        recyclerViewTopMovies.setHasFixedSize(true)
        recyclerViewTopMovies.adapter = adapterTopMovies
        recyclerViewTopMovies.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onClick(movie: Movie) {
        val detailsActivity = Intent(this, DetailsActivity::class.java).apply {
            putExtra("mv_title", movie.title)
            putExtra("mv_date_release", movie.release_date)
            putExtra("mv_overview", movie.overview)
            putExtra("mv_img_url", movie.poster_path)
        }
        startActivity(detailsActivity)
    }

    override fun onResume() {
        super.onResume()

        mViewModelMovies.getData()
        mViewModelMovies.mListTop.observe(this, { listTopMovies ->
            adapterTopMovies.updateList(listTopMovies)
        })
    }
}