package com.example.myapprapptest.usecases

import android.util.Log
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.models.MovieJSONResponse
import com.example.myapprapptest.repository.ICallbackNetworkOperation
import com.example.myapprapptest.repository.network.ConstantServer
import com.example.myapprapptest.repository.network.IAPIMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NetworkTopListMoviesImpl @Inject constructor(private val mService: IAPIMovie) {
    private var _mList : ArrayList<Movie>  = arrayListOf()
    private val disposable = CompositeDisposable()
    private lateinit var callback: ICallbackNetworkOperation

    fun setCallbackOperation(cb: ICallbackNetworkOperation){
        this.callback = cb
    }

    fun getInfo(): List<Movie>{
        disposable.add(
            mService.getMoviesList(
                ConstantServer.LIST_TOP_RATED,
                "1",
                ConstantServer.KEY_LANG_ES,
                ConstantServer.API
            )
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<MovieJSONResponse>() {

                    override fun onSuccess(t: MovieJSONResponse?) {
                       Log.d("OVM", "Network Top Success -> ${t?.results}")
                        t?.results?.let { it ->
                            _mList.addAll(it)
                            it.onEach {
                                movie ->  movie.top_rated = 1
                            }
                            callback.requestSuccess(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("OVM", "Success -> ${e?.message}")

                    }
                })
        )
        return _mList
    }

    fun onCleared(){
        disposable.clear()
    }
}
