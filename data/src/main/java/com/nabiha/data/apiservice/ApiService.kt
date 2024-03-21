package com.nabiha.data.apiservice

import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.apiresponse.users.UserApiLoginResponse
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.apiresponse.users.UserApiResponse
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //user endpoint
    @GET("/users/account/profile")
    suspend fun fetchProfile(
        @HeaderMap headers : Map<String, String>
    ):Response<UserApiResponse>

    @POST("/users/account/register")
    @Headers("Accept: application/json")
    suspend fun fetchRegisterUser(
        @Body data: UserApiRegisterRequest
    ):Response<UserApiResponse>

    @POST("/users/account/login")
    @Headers("Accept: application/json")
    suspend fun fetchLoginUser(
        @Body data: UserApiLoginRequest
    ):Response<UserApiLoginResponse>

    @PUT("/users/{id}")
    @Headers("Accept: application/json")
    suspend fun fetchUpdaterUser(
        @HeaderMap headers : Map<String, String>,
        @Path("id") id: Long,
        @Body data: UserApiUpdateRequest
    ):Response<UserApiResponse>
}