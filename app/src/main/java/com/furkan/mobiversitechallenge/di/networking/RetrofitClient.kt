package com.furkan.mobiversitechallenge.di.networking

import com.furkan.mobiversitechallenge.common.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single(named(Constants.Dictionary)) { provideDictionaryApi(get(named(Constants.BASE_URL_Dictionary))) }
    single(named(Constants.DataMuse)) { provideDataMuseApi(get(named(Constants.BASE_URL_DataMuse))) }
    single {provideLoggingInterceptor()}
    single(named(Constants.BASE_URL_Dictionary)) { provideRetrofitDictionary(get()) }
    single(named(Constants.BASE_URL_DataMuse)) { provideRetrofitDataMuse(get()) }
}

fun provideRetrofitDictionary(okHttpClient: OkHttpClient ): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL_Dictionary).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideRetrofitDataMuse(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL_DataMuse).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}

fun provideDictionaryApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

fun provideDataMuseApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)