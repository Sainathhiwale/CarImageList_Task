package com.examen.carimagetask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.data.model.UpdateCar
import com.examen.carimagetask.data.utils.UtilsResources
import com.examen.carimagetask.domain.usecase.UpdateCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarDetailsViewModel @Inject constructor(private val updateCarUseCase: UpdateCarUseCase):ViewModel() {

    private val _updateTaskState = MutableStateFlow<UtilsResources<List<CarList>>>(UtilsResources.Loading())
    val updateTaskState: StateFlow<UtilsResources<List<CarList>>> = _updateTaskState

    fun updateTask(taskId: Int, taskDetails: UpdateCar) {
        viewModelScope.launch {
            try {
                _updateTaskState.value = UtilsResources.Loading() // Emit loading state

                val responseDeferred = async { updateCarUseCase.updateCar(taskId, taskDetails) }
                val response = responseDeferred.await()
                if (response.isEmpty()){
                    _updateTaskState.value = UtilsResources.Error("Unknown error occurred")
                    return@launch
                }else{
                    _updateTaskState.value = UtilsResources.Success(response)
                }
            } catch (e: Exception) {
                Log.d("CarDetails", "updateTask: ${e.toString()}")
                _updateTaskState.value = UtilsResources.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

}