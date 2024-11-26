package com.examen.carimagetask.data.datasource

import com.examen.carimagetask.data.api.CarApiService
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val carApiService: CarApiService) : RemoteDataSource {
    override suspend fun getCarList(): MutableList<CarList> {
       return carApiService.getCarList()
    }

    override suspend fun updateTask(): List<UpdateCar> {
        return carApiService.updateTask()
    }


}