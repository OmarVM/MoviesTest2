package com.example.myapprapptest.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.models.MovieJSONResponse
import com.example.myapprapptest.repository.network.ConstantServer
import com.example.myapprapptest.repository.network.IAPIMovie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NetworkTopListMoviesImpl @Inject constructor(val mService: IAPIMovie) {
    private var _mList = MutableLiveData<List<Movie>>()
    private val disposable = CompositeDisposable()


    fun getInfo(): LiveData<List<Movie>>{
        disposable.add(
            mService.getMoviesList(
                ConstantServer.LIST_TOP_RATED,
                "1",
                ConstantServer.KEY_LANG_ES,
                ConstantServer.API
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieJSONResponse>() {

                    override fun onSuccess(t: MovieJSONResponse?) {
                       Log.d("OVM", "Success -> ${t?.results}")
                        _mList.postValue(t?.results)
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
