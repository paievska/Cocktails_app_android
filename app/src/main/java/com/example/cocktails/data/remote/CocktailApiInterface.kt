package com.example.cocktails.data.remote

import com.example.cocktails.data.remote.model.DrinkList
import com.example.cocktails.data.remote.model.DrinksByCategoryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiInterface {
    @GET("random.php")
    suspend fun getRandomDrink(): Response<DrinkList>

    @GET("lookup.php?")
    suspend fun getDrinkDetails(@Query("i") id: String): Response<DrinkList>

    @GET("filter.php?")
    suspend fun getPopularItems(@Query("c") categoryName: String): Response<DrinksByCategoryList>
}