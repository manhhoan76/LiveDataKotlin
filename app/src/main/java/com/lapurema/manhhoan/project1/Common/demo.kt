package com.lapurema.manhhoan.project1.Common

import com.lapurema.manhhoan.project1.BuildConfig
import com.lapurema.manhhoan.project1.Modal.Model
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit

public interface RequsetAPI {

    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): android.database.Observable<Model.Result>

    companion object Factory {
        fun create() {
            var retrofit: Retrofit? = null
            fun getClient(): Retrofit? {
                if (retrofit == null) {
                    val interceptor = HttpLoggingInterceptor()
                    if (BuildConfig.DEBUG) {
                        interceptor.level = HttpLoggingInterceptor.Level.BODY
                    } else {
                        interceptor.level = HttpLoggingInterceptor.Level.NONE

                    }
                    val client = OkHttpClient.Builder().apply {
                        readTimeout(20, TimeUnit.SECONDS)
                        writeTimeout(20, TimeUnit.SECONDS)
                        connectTimeout(20, TimeUnit.SECONDS)
                        addInterceptor(interceptor)
                        addInterceptor { chain ->
                            var request = chain.request()
                            request = request.newBuilder()
//                                .header("CONTENT_TYPE", "application/json")
//                                .header("LANGUAGE_CODE", "en")
                                .build()
                            val response = chain.proceed(request)
                            response
                        }
                    }
                    retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://api.openweathermap.org/data/2.5/weather")
                        .client(client.build())
                        .build()

                }
                return retrofit
            }

        }
    }
}