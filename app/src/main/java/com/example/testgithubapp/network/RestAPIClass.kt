package com.example.testgithubapp.network


import com.example.testgithubapp.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RestAPIClass {

    val apiInterface: APIInterface

    private val builder = OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)

    init {

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }

        builder.addInterceptor(Interceptor { chain ->

            val url = chain.request().url.newBuilder().build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            try {
                chain.proceed(request)
            } catch (e: Exception) {
                return@Interceptor Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(999)
                    .message(e.message.toString())
                    .body(ResponseBody.create(null, "{${e}}")).build()
            }
        })

        val apiClient = builder.build()

        val retrofit = Retrofit.Builder().client(apiClient)
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        apiInterface = retrofit.create(APIInterface::class.java)
    }

}