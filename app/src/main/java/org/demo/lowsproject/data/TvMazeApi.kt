package org.demo.lowsproject.data

import org.demo.lowsproject.data.dto.EpisodeDto
import org.demo.lowsproject.data.dto.TvShowDetailDto
import org.demo.lowsproject.data.dto.TvShowDto
import org.demo.lowsproject.data.dto.TvShowSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {
    @GET("shows")
    suspend fun getShowsByPage(@Query("page") page: Int = 0): List<TvShowDto>

    @GET("search/shows")
    suspend fun getTvShowsByText(@Query("q") query: String): List<TvShowSearchDto>

    @GET("shows/{id}")
    suspend fun getTvShowById(@Path("id") id: Int): TvShowDetailDto

    @GET("shows/{id}/episodes")
    suspend fun getTvEpisodesByShowId(@Path("id") id: Int): List<EpisodeDto>
}
