package me.mikasa.doubandemo.bean.movie

/**
 * Created by mikasa on 2018/12/26.
 */
data class MovieDetailData(
        var rating: MoviesRating,
        var wish_count: Int,
        var year: String,
        var images: MovieImages,
        var title: String,
        var countries: ArrayList<String>,
        var genres: ArrayList<String>,
        var casts: ArrayList<MoviePerson>,
        var summary: String,
        var directors: ArrayList<MoviePerson>,
        var comments_count: Int
)