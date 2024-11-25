package com.examen.carimagetask.data.api

import com.examen.carimagetask.data.model.CarList
import retrofit2.http.GET
import retrofit2.http.Headers

interface CarApiService {
    @Headers("Content-Type: application/json")
    @GET("carlist")
    suspend fun getCarList(): MutableList<CarList>


}