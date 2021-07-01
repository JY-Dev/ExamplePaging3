package com.example.examplepaging3.data.network

import com.example.examplepaging3.models.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DummyService {
    @GET("user")
    fun getUser(
        @Header("app-id") appId : String,
        @Query("page") page: Int, @Query("limit") limit: Int
    ) : Single<UserResponse>
}