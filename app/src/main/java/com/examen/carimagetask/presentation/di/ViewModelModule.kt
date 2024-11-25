package com.examen.carimagetask.presentation.di

import com.examen.carimagetask.domain.usecase.UseCase
import com.examen.carimagetask.presentation.viewmodel.CarViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCarViewModel(carListUseCase: UseCase): CarViewModel {
        return CarViewModel(carListUseCase)
    }

}