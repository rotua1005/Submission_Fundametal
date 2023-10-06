package com.example.fundametal.berkas.Api

import com.example.fundametal.berkas.Adapter.ResUser
import com.example.fundametal.berkas.Config.Config
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DataApi {

    @GET("search/users")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getSearchUsers(
        @Query("q") query : String
    ): Call<ResUser>

}