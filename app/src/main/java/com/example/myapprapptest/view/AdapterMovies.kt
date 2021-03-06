package com.example.myapprapptest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapprapptest.R
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.network.ConstantServer
import com.squareup.picasso.Picasso

class AdapterMovies (var mList: ArrayList<Movie>): RecyclerView.Adapter<AdapterMovies.TopMoviesHolder>() {
    private lateinit var mListener: IOnClickListener

    fun updateList(newList: List<Movie>){
        mList.clear()
        mList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: IOnClickListener){
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ui_item_movie, parent, false)
        return TopMoviesHolder(view)
    }

    override fun onBindViewHolder(holder: TopMoviesHolder, position: Int) {
        val movie = mList[position]
        holder.bindItem(movie, mListener)
        Picasso.get().load(ConstantServer.BASE_URL_IMG_W185 + movie.poster_path).into(holder.imgMovie)
        holder.titleMovie.text = movie.title
        holder.dateMovie.text = movie.release_date
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class TopMoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgMovie: ImageView = itemView.findViewById(R.id.item_movie_img)
        var titleMovie: TextView = itemView.findViewById(R.id.item_movie_title)
        var dateMovie: TextView = itemView.findViewById(R.id.item_movie_date)

        fun bindItem(movie: Movie, listener: IOnClickListener ){
            itemView.setOnClickListener{
                listener.onClick(movie)
            }
        }
    }

}