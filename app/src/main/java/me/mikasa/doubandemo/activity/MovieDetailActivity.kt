package me.mikasa.doubandemo.activity

import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_include_toolbar.*
import me.mikasa.doubandemo.R
import me.mikasa.doubandemo.adapter.CastAdapter
import me.mikasa.doubandemo.api.ApiService
import me.mikasa.doubandemo.base.BaseActivity
import me.mikasa.doubandemo.bean.movie.MovieDetailData
import me.mikasa.doubandemo.bean.movie.MoviePerson
import me.mikasa.doubandemo.http.BaseSubscriber
import me.mikasa.doubandemo.http.RetrofitFactory
import me.mikasa.doubandemo.http.execute

class MovieDetailActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_movie_detail
    }

    override fun initData() {
        RetrofitFactory.instance.create(ApiService::class.java)
                .getMovieDetail(intent.getStringExtra("id"))
                .execute(object : BaseSubscriber<MovieDetailData>() {
                    override fun onNext(t: MovieDetailData) {
                        setData(t)
                    }
                })
    }

    override fun initView() {
        setToolBar(include_toolbar)
        include_toolbar_tv.text=intent.getStringExtra("title")
    }
    private fun setData(t:MovieDetailData){
        mTvMovieName.text = t.title
        mTvCountries.text = "${t.countries} · ${t.year}"
        mTvLove.text = String.format(mTvLove.text as String, t.wish_count)
        mTvComment.text = String.format(mTvComment.text as String, t.comments_count)
        Glide.with(this)
                .load(t.images.large)
                .centerCrop()
                .into(mIvMoviePoster)
        mTvScore.text = String.format(mTvScore.text as String, t.rating.average)
        val directors = getDirectors(t.directors)
        mTvDirectors.text = String.format(mTvDirectors.text as String, directors)
        val casts = getCasts(t.casts)
        mTvCasts.text = "${mTvCasts.text}: $casts"
        val genres = getGenres(t.genres)
        mTvGenres.text = String.format(mTvGenres.text as String, genres)
        // 　\u3000\u3000  首行缩进
        mTvSummary.text = "\u3000\u3000 ${t.summary}"
        initCasts(t.casts)
    }
    private fun initCasts(casts:ArrayList<MoviePerson>){
        val manager=LinearLayoutManager(this)
        manager.orientation=LinearLayoutManager.HORIZONTAL
        mCastsRecyclerView.layoutManager=manager
        val adapter=CastAdapter()
        mCastsRecyclerView.adapter=adapter
        adapter.refreshData(casts)
    }
    private fun getGenres(genresList: ArrayList<String>): String {
        var genres = ""
        for (genre in genresList) {
            genres = "$genres、$genre"
        }
        return genres.substring(1, genres.length)
    }

    private fun getCasts(castsList: ArrayList<MoviePerson>): String {
        var casts = ""
        for (cast in castsList) {
            casts = "$casts/${cast.name}"
        }
        return casts.substring(0, casts.length - 1)
    }

    private fun getDirectors(directorsList: ArrayList<MoviePerson>): String {
        var directors = ""
        for (director in directorsList) {
            directors = "$directors、${director.name}"
        }
        return directors.substring(1, directors.length)
    }
}
