package com.examen.carimagetask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.carimagetask.data.model.CarList
import com.examen.carimagetask.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(private val useCase: UseCase):ViewModel() {

    private val _carList = MutableLiveData<List<CarList>>()
    val carList: LiveData<List<CarList>> get() = _carList

    fun fetchCars() {
        viewModelScope.launch {
            try {
                val carListDeferred = async { useCase.getCarList() }
                _carList.value = carListDeferred.await() // Await the result

            } catch (e: Exception) {
                Log.d("ViewModel", "fetchCars: {${e.toString()}")
            }
        }
    }

}