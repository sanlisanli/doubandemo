package me.mikasa.doubandemo.api

import me.mikasa.doubandemo.bean.movie.MovieDetailData
import me.mikasa.doubandemo.bean.movie.MoviesData
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by mikasa on 2018/12/26.
 */
interface ApiService {
    @GET("/v2/movie/in_theaters")
    fun getTheaterMovie():Observable<MoviesData>

    @GET("/v2/movie/coming_soon")
    fun getComingMovie():Observable<MoviesData>

    @GET("/v2/movie/top250")
    fun getTopMovie():Observable<MoviesData>

    @GET("/v2/movie/subject/{id}")
    fun getMovieDetail(@Path("id")id:String):Observable<MovieDetailData>

}