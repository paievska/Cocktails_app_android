package com.example.cocktails.retrofit

import com.example.cocktails.pojo.DrinkList
import com.example.cocktails.pojo.DrinksByCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {
    @GET("random.php")
    fun getRandomDrink(): Call<DrinkList>

    @GET("lookup.php?")
    fun getDrinkDetails(@Query("i") id: String): Call<DrinkList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<DrinksByCategoryList>


}