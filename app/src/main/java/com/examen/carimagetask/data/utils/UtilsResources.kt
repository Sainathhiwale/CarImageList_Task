package com.examen.carimagetask.data.utils

sealed class UtilsResources<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : UtilsResources<T>(data)
    class Loading<T>(data : T? = null) : UtilsResources<T>(data)
    class Error<T>(message : String, data: T? = null) : UtilsResources<T>(data,message)

}