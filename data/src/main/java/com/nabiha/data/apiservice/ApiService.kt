package com.nabiha.data.apiservice

import com.nabiha.apiresponse.carts.CartApiDelResponse
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.apiresponse.carts.CartApiResponse
import com.nabiha.apiresponse.carts.CartsApiResponse
import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoriesApiResponse
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.apiresponse.histories.HistoryApiResponse
import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.apiresponse.likes.LikeApiResponse
import com.nabiha.apiresponse.likes.LikesApiResponse
import com.nabiha.apiresponse.likes.UnlikeApiResponse
import com.nabiha.apiresponse.products.ProductApiResponse
import com.nabiha.apiresponse.products.ProductsApiResponse
import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.apiresponse.users.UserApiLoginResponse
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.apiresponse.users.UserApiResponse
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

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
    @Multipart
    suspend fun fetchUpdaterUser(
        @HeaderMap headers : Map<String, String>,
        @Path("id") id: Long,
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("date_birth") dateBirth: RequestBody,
        @Part image: MultipartBody.Part
    ):Response<UserApiResponse>


    @GET("/products")
    suspend fun fetchProducts(
        @HeaderMap headers : Map<String, String>
    ):Response<ProductsApiResponse>

    @GET("/products/{id}")
    suspend fun fetchProduct(
        @HeaderMap headers : Map<String, String>,
        @Path("id") id: Long
    ):Response<ProductApiResponse>

    @GET("/likes")
    suspend fun fetchWhistLists(
        @HeaderMap headers : Map<String, String>,
    ): Response<LikesApiResponse>

    @POST("/likes")
    suspend fun likeProduct(
        @HeaderMap headers : Map<String, String>,
        @Body request: LikeApiRequest
    ): Response<LikeApiResponse>

    @DELETE("/likes/{id}")
    suspend fun unlikeProduct(
        @HeaderMap headers : Map<String, String>,
        @Path("id") id: Long
    ): Response<UnlikeApiResponse>

    @GET("carts/{id}")
    suspend fun fetchCart(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Long
    ): Response<CartApiResponse>

    @PUT("carts/{id}")
    suspend fun updateCart(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Long,
        @Body request: CartApiRequest
    ): Response<CartApiResponse>

    @DELETE("carts/{id}")
    suspend fun deleteCart(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Long
    ): Response<CartApiDelResponse>

    @DELETE("carts")
    suspend fun deleteAllCart(
        @HeaderMap headers: Map<String, String>
    ): Response<CartApiDelResponse>

    @GET("carts")
    suspend fun fetchCarts(
        @HeaderMap headers: Map<String, String>
    ):Response<CartsApiResponse>

    @POST("carts")
    suspend fun createCarts(
        @HeaderMap headers: Map<String, String>,
        @Body request: CartApiRequest
    ): Response<CartApiResponse>

    @POST("histories")
    suspend fun createHistories(
        @HeaderMap headers: Map<String, String>,
        @Body request: HistoriesApiRequest
    ): Response<HistoriesApiResponse>

    @GET("histories")
    suspend fun fetchHistories(
        @HeaderMap headers: Map<String, String>,
    ): Response<HistoriesApiResponse>

    @GET("histories/{id}")
    suspend fun fetchHistory(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Long
    ): Response<HistoryApiResponse>

}