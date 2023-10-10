package com.shafik.anyline.retrofit

import com.shafik.anyline.util.MyExtensionFunctions.printToLog
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val response = chain.proceed(originalRequest)
        "[${originalRequest.method}]; ${response.code}; ${originalRequest.url}".printToLog("interceptor")
        return response
    }
}