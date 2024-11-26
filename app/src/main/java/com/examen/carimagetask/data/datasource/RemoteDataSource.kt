package com.examen.carimagetask.data.datasource

import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar


interface RemoteDataSource {
    suspend fun getCarList(): MutableList<CarList>
    suspend fun updateTask():List<UpdateCar>


}