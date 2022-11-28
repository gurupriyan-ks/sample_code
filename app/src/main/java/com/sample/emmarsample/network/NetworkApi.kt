package com.sample.emmarsample.network

import com.sample.emmarsample.models.servicemodels.Users
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("api/")
    fun getUsers(
        @Query("page") page: Int = 0,
        @Query("results") results: Int = 20,
        @Query("seed") seed: String = "abc"
    ): Deferred<Response<Users>>
}