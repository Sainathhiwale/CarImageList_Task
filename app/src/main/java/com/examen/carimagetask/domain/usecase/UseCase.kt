package com.examen.carimagetask.domain.usecase

import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.domain.repository.CarRepository
import javax.inject.Inject

class UseCase @Inject constructor(private val carRepository: CarRepository) {

    suspend fun getCarList():MutableList<CarList>{
        return carRepository.getCarList()
    }

}