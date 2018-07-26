package com.app.kotlinbasews.api


import com.app.kotlinbasews.helper.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {
    companion object {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .retryOnConnectionFailure(false)
                .build()

        fun getService(): AppApi {
            var retrofit = Retrofit.Builder()
                    .baseUrl(Constants.getBaseHost())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            return retrofit.create<AppApi>(AppApi::class.java)
        }


    }
}
