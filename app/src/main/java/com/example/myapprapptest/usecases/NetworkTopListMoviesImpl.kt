package com.example.myapprapptest.usecases

import android.util.Log
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.models.MovieJSONResponse
import com.example.myapprapptest.repository.network.ConstantServer
import com.example.myapprapptest.repository.network.IAPIMovie
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkTopListMoviesImpl @Inject constructor(private val mService: IAPIMovie) {
    private val disposable = CompositeDisposable()

    fun getInfo() = callbackFlow<List<Movie>>{
        disposable.add(
            mService.getMoviesList(
                ConstantServer.LIST_TOP_RATED,
                "1",
                ConstantServer.KEY_LANG_ES,
                ConstantServer.API)
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<MovieJSONResponse>() {

                    override fun onSuccess(t: MovieJSONResponse?) {
                       Log.d("OVM", "Network Top Success -> ${t?.results}")
                        t?.results?.let { it ->
                            it.onEach {
                                movie ->  movie.top_rated = 1
                            }
                            sendBlocking(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("OVM", "Success -> ${e?.message}")
                    }
                }).also { awaitClose() }
        )
    }

    fun onCleared(){
        disposable.clear()
    }
}
