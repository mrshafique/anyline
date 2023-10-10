package com.shafik.anyline.screens

import com.shafik.anyline.model.ApiResponse
import com.shafik.anyline.retrofit.service.ApiService
import com.shafik.anyline.util.Constants
import com.shafik.anyline.util.MyExtensionFunctions.printToLog
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call

/**
 * We have injected ApiService interface by using koin library
 * @author Shafik Shaikh
 * */
class MainRepo(private val apiService: ApiService) {

    fun uploadImage(
        strUuid: String,
        byteArrayImage: ByteArray,
        strFileName: String
    ): Call<ApiResponse> {
        val imageRequestBody = byteArrayImage
            .toRequestBody(Constants.mediaTypeImageJpg, 0, byteArrayImage.size)
        val imagePart = MultipartBody.Part
            .createFormData("filename", strFileName, imageRequestBody)
        return apiService.uploadImage(strUuid = strUuid, imagePart = imagePart)
    }

    fun finalizeApi(
        strUuid: String
    ): Call<ApiResponse> {
        "finalizeApi".printToLog("mainRepo")
        return apiService.finalizeApi(strUuid = strUuid)
    }
}