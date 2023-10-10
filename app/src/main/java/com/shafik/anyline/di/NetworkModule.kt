package com.shafik.anyline.di

import com.shafik.anyline.retrofit.TokenInterceptor
import com.shafik.anyline.retrofit.service.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .readTimeout(100, TimeUnit.SECONDS)
        .connectTimeout(100, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://dev-task-a7benwhi7q-ez.a.run.app/")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    single {
        retrofit.create(ApiService::class.java)
    }
}