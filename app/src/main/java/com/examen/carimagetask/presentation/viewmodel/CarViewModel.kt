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

    // passing data one fragment to other fragment using shared view model
    private val _selectedCarDetails = MutableLiveData<CarList>()
    val selectedCarDetails: LiveData<CarList> = _selectedCarDetails
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

    // passing data one fragment to other fragment using shared view model set the CarList object
    fun setSelectedCar(carList: CarList) {
        _selectedCarDetails.value = carList
    }
    // passing data one fragment to other fragment using shared view model get the CarList object
    fun getSelectedCar(): CarList? {
        return _selectedCarDetails.value
    }
}