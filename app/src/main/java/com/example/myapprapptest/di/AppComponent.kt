package com.example.myapprapptest.di

import com.example.myapprapptest.viewmodel.ViewModelMovies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(viewModel: ViewModelMovies)
}