package com.arpit.newsapp3

import com.arpit.themealdbapi.ResponseMeal
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val base_url = "https://www.themealdb.com/api/json/v1/"


interface APIService {
    @GET("1/search.php?")
     suspend fun getMeal(@Query("s") s :String) : Response<ResponseMeal>
}

object  mealService {
    val apiService : APIService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }
}



