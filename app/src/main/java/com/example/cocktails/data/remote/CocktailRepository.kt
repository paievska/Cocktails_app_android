package com.example.cocktails.data.remote

import com.example.cocktails.data.remote.model.DrinkList
import com.example.cocktails.data.remote.model.DrinksByCategoryList
import retrofit2.Response

object CocktailRepository {
    private val cocktailApi = ApiFactory.cocktailApi

    suspend fun getRandomDrink(): Response<DrinkList> {
        return cocktailApi.getRandomDrink()
    }

    suspend fun getDrinkDetails(id: String): Response<DrinkList> {
        return cocktailApi.getDrinkDetails(id)
    }

    suspend fun getPopularItems(categoryName: String): Response<DrinksByCategoryList> {
        return cocktailApi.getPopularItems(categoryName)
    }
}