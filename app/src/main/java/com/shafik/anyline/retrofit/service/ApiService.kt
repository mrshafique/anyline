package com.shafik.anyline.retrofit.service

import com.shafik.anyline.model.ApiResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @Multipart
    @POST("{strUuid}/upload")
    fun uploadImage(
        @Path("strUuid") strUuid: String,
        @Part imagePart: MultipartBody.Part
    ): Call<ApiResponse> //for call, don't user suspend keyword

    @POST("{strUuid}/finish")
    fun finalizeApi(
        @Path("strUuid") strUuid: String
    ): Call<ApiResponse> //for call, don't user suspend keyword; there is no part then remove @Multipart annotation
}