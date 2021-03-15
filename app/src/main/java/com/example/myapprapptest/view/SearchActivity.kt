package com.example.myapprapptest.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapprapptest.BaseApplication
import com.example.myapprapptest.R
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.viewmodel.ViewModelSearch
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), IOnClickListener, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    private lateinit var mViewModelSearch: ViewModelSearch
    var listFull: ArrayList<Movie> = arrayListOf()

    @Inject
    lateinit var adapterMovies: AdapterMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mViewModelSearch = ViewModelProvider(this).get(ViewModelSearch::class.java)
        BaseApplication.getAppComponent().inject(mViewModelSearch)
        BaseApplication.getAppComponent().inject(this)

        adapterMovies.setClickListener(this)
        rv_search_movie.setHasFixedSize(true)
        rv_search_movie.adapter = adapterMovies
        rv_search_movie.layoutManager = GridLayoutManager(applicationContext, 3)
    }

    override fun onResume() {
        super.onResume()
        mViewModelSearch.getData()
        mViewModelSearch.mListMoviesLD.observe(this, { listMovies ->
            if (listMovies.isNotEmpty()){
                listFull.clear()
                listFull.addAll(listMovies)
                adapterMovies.updateList(listMovies)
            }
        })
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
            inflate.inflate(R.menu.menu_search_action, menu)

        val searchItem = menu?.findItem(R.id.action_search_menu_item)
        val searchView = searchItem?.actionView as SearchView
            searchView.queryHint = getString(R.string.hint_search)

        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(this)
        searchView.isIconified = false
        return true

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       if (listFull.isNotEmpty() && !newText.isNullOrBlank()){
           mViewModelSearch.searchMovie(listFull, newText)
       }
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        //if (listFull.isNotEmpty()){
        //    adapterMovies.updateList(listFull)
        //}
       return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }
}