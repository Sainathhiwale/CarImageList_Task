package com.examen.carimagetask.data.model

import com.google.gson.annotations.SerializedName

data class UpdateCar(@SerializedName("id"        ) var id       : Int?    = null,
                     @SerializedName("name"      ) var name     : String? = null,
                     @SerializedName("brand"     ) var brand    : String? = null,
                     @SerializedName("price"     ) var price    : Int?    = null)
