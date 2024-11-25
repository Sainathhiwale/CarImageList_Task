package com.examen.carimagetask.domain.repository

import com.examen.carimagetask.data.model.CarList

interface CarRepository {

    suspend fun getCarList(): MutableList<CarList>
}