package com.examen.carimagetask.data.api

import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CarApiService {
    @Headers("Content-Type: application/json")
    @GET("carlist")
    suspend fun getCarList(): MutableList<CarList>
    @PUT("carlist/id")
    suspend fun updateTask(@Path("id") taskId: Int,
        @Body taskDetails: UpdateCar
    ): List<CarList>



}