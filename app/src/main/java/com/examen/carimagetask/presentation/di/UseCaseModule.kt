package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.domain.repository.CarRepository
import com.examen.carimagetask.domain.usecase.UpdateCarUseCase
import com.examen.carimagetask.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesUseCase(carRepository: CarRepository): UseCase {
        return UseCase(carRepository)
    }
    @Singleton
    @Provides
    fun provideCarUpdateUseCase(carRepository: CarRepository): UpdateCarUseCase {
        return  UpdateCarUseCase(carRepository)
    }

}