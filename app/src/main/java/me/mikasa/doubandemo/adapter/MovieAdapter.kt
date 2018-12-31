package me.mikasa.doubandemo.adapter

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import me.mikasa.doubandemo.R
import me.mikasa.doubandemo.bean.movie.Movie

/**
 * Created by mikasa on 2018/12/26.
 */
class MovieAdapter:BaseRvAdapter<Movie>() {
    override fun getItemLayoutResId(): Int {
        return R.layout.item_movie
    }

    override fun onCreateViewHolder(root: View, viewType: Int): ViewHolder {
        return ViewHolder(root)
    }
    class ViewHolder(root:View):BaseRvAdapter.ViewHolder<Movie>(root){
        val context=root.context
        val moviePoster=root.movie_poster
        var movieAverage=root.movie_average
        var movieName=root.movie_name
        var prefix=root.resources.getString(R.string.cinema_score)
        override fun bindData(data: Movie) {
            Glide.with(context).load(data.images.large)
                    .centerCrop().into(moviePoster)
            movieName.text=data.title
            movieAverage.text = "${prefix} ${data.rating.average}"
        }
    }

}