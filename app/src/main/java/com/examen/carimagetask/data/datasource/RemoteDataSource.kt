package com.examen.carimagetask.data.datasource

import com.examen.carimagetask.data.model.CarList

interface RemoteDataSource {
    suspend fun getCarList(): MutableList<CarList>
}