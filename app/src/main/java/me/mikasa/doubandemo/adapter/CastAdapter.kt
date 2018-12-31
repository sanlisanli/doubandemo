package me.mikasa.doubandemo.adapter

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_cast.view.*
import me.mikasa.doubandemo.R
import me.mikasa.doubandemo.bean.movie.MoviePerson

/**
 * Created by mikasa on 2018/12/27.
 */
class CastAdapter :BaseRvAdapter<MoviePerson>() {
    override fun getItemLayoutResId(): Int {
        return R.layout.item_movie_cast
    }

    override fun onCreateViewHolder(root: View, viewType: Int): ViewHolder {
        return ViewHolder(root)
    }
    class ViewHolder(root:View):BaseRvAdapter.ViewHolder<MoviePerson>(root){
        val context=root.context
        val name=root.tv_cast_name
        val cast=root.iv_cast_avatar
        override fun bindData(data: MoviePerson) {
            Glide.with(context)
                    .load(data.avatars.large)
                    .centerCrop()
                    .into(cast)
            name.text=data.name
        }
    }
}