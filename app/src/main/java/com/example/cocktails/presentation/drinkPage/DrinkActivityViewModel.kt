package com.example.cocktails.presentation.drinkPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.data.remote.CocktailRepository
import com.example.cocktails.data.remote.model.Drink
import kotlinx.coroutines.launch

class DrinkActivityViewModel : ViewModel() {
    private var drinkDetailsLiveData = MutableLiveData<Drink>()

    fun getDrinkDetail(id: String) {
        viewModelScope.launch {
            val response = CocktailRepository.getDrinkDetails(id)
            if (response.isSuccessful) {
                drinkDetailsLiveData.value = response.body()!!.drinks[0]
            } else {
                Log.d("DrinkActivity", response.message())
            }
        }
    }

    fun observeDrinkDetailsLiveData(): LiveData<Drink> {
        return drinkDetailsLiveData
    }
}