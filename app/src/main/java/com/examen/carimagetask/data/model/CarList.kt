package com.examen.carimagetask.data.model

import com.google.gson.annotations.SerializedName

data class CarList(
    @SerializedName("id"        ) var id       : Int?    = null,
    @SerializedName("name"      ) var name     : String? = null,
    @SerializedName("brand"     ) var brand    : String? = null,
    @SerializedName("price"     ) var price    : Int?    = null,
    @SerializedName("image_url" ) var imageUrl : String? = null
)
