package com.doniapr.moviecatalogue.data.source.remote.apiservice

import com.doniapr.moviecatalogue.BuildConfig
import com.doniapr.moviecatalogue.data.source.local.Movie
import com.doniapr.moviecatalogue.data.source.local.TvShow
import com.doniapr.moviecatalogue.data.source.remote.response.MovieResponse
import com.doniapr.moviecatalogue.data.source.remote.response.ReviewResponse
import com.doniapr.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Movie
    @GET("movie/now_playing?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getNowPlayingMovie(): Response<MovieResponse>

    @GET("movie/popular?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MovieResponse>

    @GET("movie/upcoming?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getUpcomingMovie(): Response<MovieResponse>

    @GET("search/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1&include_adult=false&query={query}")
    suspend fun getSearchMovie(@Query("query") query: String): Response<MovieResponse>

    @GET("movie/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailMovie(@Path("id") id: String): Response<Movie>

    @GET("movie/{id}/reviews?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getReviewMovie(@Path("id") id: String): Response<ReviewResponse>

    // Tv
    @GET("tv/on_the_air?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getOnAirTvShow(): Response<TvShowResponse>

    @GET("tv/airing_today?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getAiringTodayTvShow(): Response<TvShowResponse>

    @GET("tv/popular?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getPopularTvShow(): Response<TvShowResponse>

    @GET("search/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1&include_adult=false&query={query}")
    suspend fun getSearchTvShow(@Query("query") query: String): Response<TvShowResponse>

    @GET("tv/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailTvShow(@Path("id") id: String): Response<TvShow>

    @GET("tv/{id}/reviews?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getReviewTvShow(@Path("id") id: String): Response<ReviewResponse>
}