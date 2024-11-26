package com.examen.carimagetask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examen.carimagetask.data.model.CarList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedCarViewModel @Inject constructor():ViewModel() {
    private var carList = MutableLiveData<CarList>()
    fun setCarList(list:CarList){
        carList.value = list
    }
    fun getCarList():MutableLiveData<CarList> {
        return carList
    }
}