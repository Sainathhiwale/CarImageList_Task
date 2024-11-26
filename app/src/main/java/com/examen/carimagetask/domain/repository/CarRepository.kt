package com.examen.carimagetask.domain.repository

import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar


interface CarRepository {

    suspend fun getCarList(): MutableList<CarList>

    suspend fun updateTask(id:Int, updateCar: UpdateCar):MutableList<CarList>

}