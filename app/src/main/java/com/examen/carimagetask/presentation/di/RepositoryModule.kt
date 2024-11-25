package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.data.datasource.RemoteDataSource
import com.examen.carimagetask.data.datasource.repository.CarRepositoryImpl
import com.examen.carimagetask.domain.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): CarRepository {
        return CarRepositoryImpl(remoteDataSource)
    }
}