package me.mikasa.doubandemo.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by mikasa on 2018/12/26.
 */
class RetrofitFactory private constructor(){
    private val retrofit:Retrofit
    companion object {
        val instance:RetrofitFactory by lazy {
            RetrofitFactory()
        }
    }
    init {
        retrofit=Retrofit.Builder()
                .baseUrl("https://api.douban.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initOkHttpClient())
                .build()
    }
    fun<T>create(service:Class<T>):T{
        return retrofit.create(service)
    }
    private fun initOkHttpClient():OkHttpClient?{
        return OkHttpClient.Builder().connectTimeout(10,TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).build()
    }
}