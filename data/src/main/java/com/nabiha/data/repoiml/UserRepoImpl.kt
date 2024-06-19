package com.nabiha.data.repoiml

import android.content.Context
import android.net.Uri
import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.createImagePart
import com.nabiha.common.utils.createPartFromString
import com.nabiha.data.apiservice.ApiService
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.data.mapper.user.UserLoginMapper
import com.nabiha.data.mapper.user.UserMapper
import com.nabiha.data.utils.NetworkBoundResource
import com.nabiha.data.utils.mapFromApiResponse
import com.nabiha.domain.repository.UserRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import com.nabiha.entity.UserEntityLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkBoundResources: NetworkBoundResource,
    private val userMapper: UserMapper,
    private val userLoginMapper: UserLoginMapper,
    private val preferenceDatastore: PreferenceDatastore
) : UserRepository {

    override suspend fun fetchUserProfile(): Flow<Result<UserEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchProfile(
                    headers = mapOf("Authorization" to "Bearer $token")
                )
            }, userMapper
        )
    }

    override suspend fun fetchRegisterUser(params: UserApiRegisterRequest): Flow<Result<UserEntity>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchRegisterUser(params)
            }, userMapper
        )
    }

    override suspend fun fetchLoginUser(params: UserApiLoginRequest): Flow<Result<UserEntityLogin>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchLoginUser(params)
            }, userLoginMapper
        )

    }

    override suspend fun fetchUpdaterUser(
        params: Long,
        context: Context,
        data: UserApiUpdateRequest
    ): Flow<Result<UserEntity>> {
        val token = preferenceDatastore.getToken().first()

        // Convert string fields to RequestBody
        val namePart = createPartFromString(data.name)
        val phonePart = createPartFromString(data.phone)
        val genderPart = createPartFromString(data.gender)
        val dateBirthPart = createPartFromString(data.date_birth)

        val imagePart = createImagePart(data.image, context)

        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchUpdaterUser(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = params,
                    name = namePart,
                    phone = phonePart,
                    gender= genderPart,
                    dateBirth = dateBirthPart,
                    image = imagePart
                )
            }, userMapper
        )
    }

}