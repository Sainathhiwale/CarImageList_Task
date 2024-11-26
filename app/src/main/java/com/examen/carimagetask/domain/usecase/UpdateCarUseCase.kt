package com.examen.carimagetask.domain.usecase

import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar
import com.examen.carimagetask.domain.repository.CarRepository
import javax.inject.Inject

class UpdateCarUseCase @Inject constructor(private val carRepository: CarRepository) {

    suspend fun updateCar(taskId:Int, updateCar: UpdateCar): List<CarList> {
        return carRepository.updateTask(taskId,updateCar)
    }
}