package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.data.api.CarApiService
import com.examen.carimagetask.data.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance():Retrofit{
      val interceptor = HttpLoggingInterceptor().apply {
         this.level = HttpLoggingInterceptor.Level.BODY
      }
       val client = OkHttpClient.Builder().apply {
          this.addInterceptor(interceptor)
              .connectTimeout(10, TimeUnit.SECONDS)
              .readTimeout(10, TimeUnit.SECONDS)
              .writeTimeout(15, TimeUnit.SECONDS)
       }.build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(AppConstants.BASE_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideCarAPiService(retrofit: Retrofit): CarApiService {
        return retrofit.create(CarApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()
}