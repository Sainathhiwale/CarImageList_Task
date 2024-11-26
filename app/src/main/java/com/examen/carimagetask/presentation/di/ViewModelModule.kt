package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.domain.usecase.UpdateCarUseCase
import com.examen.carimagetask.domain.usecase.UseCase
import com.examen.carimagetask.presentation.viewmodel.CarDetailsViewModel
import com.examen.carimagetask.presentation.viewmodel.CarViewModel
import com.examen.carimagetask.presentation.viewmodel.SharedCarViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun provideCarViewModel(carListUseCase: UseCase): CarViewModel {
        return CarViewModel(carListUseCase)
    }
    @Singleton
    @Provides
    fun provideSharedViewModel():SharedCarViewModel{
        return SharedCarViewModel()
    }
    @Singleton
    @Provides
    fun provideCarDetailsViewModel(updateCarUseCase: UpdateCarUseCase): CarDetailsViewModel{
        return CarDetailsViewModel(updateCarUseCase)
    }


}