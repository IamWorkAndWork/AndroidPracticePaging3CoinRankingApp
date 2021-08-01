package app.practice.cryptowongnaitest.di

import app.practice.cryptowongnaitest.BuildConfig
import app.practice.cryptowongnaitest.data.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.coinranking.com/"

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BASE_URL)
            .build()
    }

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
    }

    single<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

}