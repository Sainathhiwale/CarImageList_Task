package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.data.api.CarApiService
import com.examen.carimagetask.data.datasource.RemoteDataSource
import com.examen.carimagetask.data.datasource.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    // remote source dependency object
    @Singleton
    @Provides
    fun provideRemoteDataSource(carApiService: CarApiService): RemoteDataSource {
        return RemoteDataSourceImpl(carApiService)
    }
}