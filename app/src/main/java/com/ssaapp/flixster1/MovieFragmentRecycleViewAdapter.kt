package com.ssaapp.flixster1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieFragmentRecyclerViewAdapter (
    private val movies :List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<MovieFragmentRecyclerViewAdapter.MovieViewHolder>()
{

    inner class MovieViewHolder(val mView : View) : RecyclerView.ViewHolder(mView) {
        var mItem : Movie?= null
        val mMovieTitle : TextView = mView.findViewById<View>(R.id.title) as TextView
        val mMovieOverview : TextView = mView.findViewById<View>(R.id.description) as TextView
        val mMoviePoster : ImageView = mView.findViewById<View>(R.id.image) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieOverview.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_fragment, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieOverview.text = movie.overview

        Glide.with(holder.mView)
            .load( "https://image.tmdb.org/t/p/w500/" + movie.imageUrl)
            .centerInside()
            .into(holder.mMoviePoster)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}