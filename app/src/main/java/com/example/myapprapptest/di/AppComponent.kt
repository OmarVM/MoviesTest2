package com.example.myapprapptest.di

import com.example.myapprapptest.view.MainActivity
import com.example.myapprapptest.view.SearchActivity
import com.example.myapprapptest.viewmodel.ViewModelMovies
import com.example.myapprapptest.viewmodel.ViewModelSearch
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(viewModel: ViewModelMovies)

    //Search View
    fun inject(searchActivity: SearchActivity)
    fun inject(viewModelSearch: ViewModelSearch)
}