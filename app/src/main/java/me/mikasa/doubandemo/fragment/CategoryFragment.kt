package me.mikasa.doubandemo.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_category.*
import me.mikasa.doubandemo.R
import me.mikasa.doubandemo.activity.MovieDetailActivity
import me.mikasa.doubandemo.adapter.BaseRvAdapter
import me.mikasa.doubandemo.adapter.MovieAdapter
import me.mikasa.doubandemo.api.ApiService
import me.mikasa.doubandemo.base.BaseFragment
import me.mikasa.doubandemo.bean.movie.Movie
import me.mikasa.doubandemo.bean.movie.MoviesData
import me.mikasa.doubandemo.constant.Constants
import me.mikasa.doubandemo.http.BaseSubscriber
import me.mikasa.doubandemo.http.RetrofitFactory
import me.mikasa.doubandemo.http.execute
import rx.Observable

/**
 * Created by mikasa on 2018/12/26.
 */
class CategoryFragment:BaseFragment() {
    private var type:Int=0
    private lateinit var adapter:MovieAdapter
    companion object {
        fun newInstance(type:Int):CategoryFragment{
            val fragment=CategoryFragment()
            fragment.type=type
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        category_movie_rv.layoutManager=GridLayoutManager(context,2)
        adapter= MovieAdapter()
        category_movie_rv.adapter=adapter
        adapter.setOnItemClickListener(object : BaseRvAdapter.OnItemClickListener<Movie> {
            override fun onItemClick(holder: BaseRvAdapter.ViewHolder<Movie>, data: Movie) {
                //val id=data.id
                val intent=Intent(context,MovieDetailActivity::class.java)
                intent.putExtra("title",data.title)
                intent.putExtra("id",data.id)
                context?.startActivity(intent)
            }
        })
    }

    override fun initData() {

        var observable=RetrofitFactory.instance.create(ApiService::class.java)
        var rspObservable:Observable<MoviesData>?=null
        when(type){
            Constants.THEATER_MOVIE->rspObservable=observable.getTheaterMovie()
            Constants.COMING_MOVIE->rspObservable=observable.getComingMovie()
            Constants.TOP_MOVIE->rspObservable=observable.getTopMovie()
        }
        rspObservable?.execute(object : BaseSubscriber<MoviesData>() {
            override fun onNext(t:MoviesData){
                adapter.refreshData(t.subjects)
            }
        })
    }
}