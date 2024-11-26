package com.examen.carimagetask.data.datasource.repository

import com.examen.carimagetask.data.datasource.RemoteDataSource
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar
import com.examen.carimagetask.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : CarRepository {
    override suspend fun getCarList(): MutableList<CarList> {
        return remoteDataSource.getCarList()
    }
    override suspend fun updateTask(id: Int, updateCar: UpdateCar): MutableList<CarList> {
       return remoteDataSource.updateTask(id,updateCar)
    }

}

