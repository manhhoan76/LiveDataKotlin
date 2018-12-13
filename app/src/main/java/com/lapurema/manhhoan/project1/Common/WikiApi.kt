package com.lapurema.manhhoan.project1.Common

import com.lapurema.manhhoan.project1.BuildConfig
import com.lapurema.manhhoan.project1.Modal.Model
import com.lapurema.manhhoan.project1.Modal.ObjLogin
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WikiApi {

    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): Observable<Model.Result>

    @POST("login")
    fun loginWallet(@Header("auth") auth: String
    ): Observable<ObjLogin.ResultLogin>



    companion object {
        fun creatClient(): OkHttpClient.Builder {
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
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .header("locale", "en")
//                        .header("auth", "OWE3MzQwMWM5OGJmZDkyY2EyMjMyZTQ5N2Y4OTgxN2Q6bmd1eWVuMQ==")
                        .header("currency_unit", "USD")
                        .build()
                    val response = chain.proceed(request)
                    response
                }
            }
            return client
        }
        fun create(): WikiApi {


            val retrofit = Retrofit.Builder()
                .baseUrl("https://ibkwallet.com:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(creatClient().build())
                .build()
            return retrofit.create(WikiApi::class.java)
        }
    }
}