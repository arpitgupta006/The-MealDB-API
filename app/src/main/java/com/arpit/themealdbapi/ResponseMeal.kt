package com.arpit.themealdbapi


import com.google.gson.annotations.SerializedName

data class ResponseMeal(
    @SerializedName("meals")
    val meals: List<Meal>?
)