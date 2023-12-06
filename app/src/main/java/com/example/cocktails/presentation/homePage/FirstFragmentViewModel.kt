package com.example.cocktails.presentation.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.data.remote.CocktailRepository
import com.example.cocktails.data.remote.model.Drink
import com.example.cocktails.data.remote.model.DrinksByCategory
import kotlinx.coroutines.launch

class FirstFragmentViewModel : ViewModel() {
    private var randomDrinkLiveData = MutableLiveData<Drink>()
    private var popularItemsLiveData = MutableLiveData<List<DrinksByCategory>>()

    fun getRandomDrink() {
        viewModelScope.launch {
            val response = CocktailRepository.getRandomDrink()
            if (response.isSuccessful) {
                val randomDrink: Drink = response.body()!!.drinks[0]
                randomDrinkLiveData.value = randomDrink
            }
        }
    }

    fun getPopularItems() {
        viewModelScope.launch {
            val response = CocktailRepository.getPopularItems("Ordinary_Drink")
            if (response.isSuccessful) {
                popularItemsLiveData.value = response.body()!!.drinks
            }
        }
    }

    fun observeRandomDrinkLiveData(): LiveData<Drink> {
        return randomDrinkLiveData
    }

    fun observePopularDrinkLiveData(): LiveData<List<DrinksByCategory>> {
        return popularItemsLiveData
    }
}