package com.sobolev.wocab.data.remote

import com.sobolev.wocab.data.dto.DailyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyApi {
    @GET("api/daily")
    suspend fun getDaily(
        @Query("date") date: String? = null
    ): DailyDto

    @GET("api/daily/range")
    suspend fun getDailyRange(
        @Query("start") start: String,
        @Query("end") end: String
    ): List<DailyDto>
}



