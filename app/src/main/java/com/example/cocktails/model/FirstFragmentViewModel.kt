package com.example.cocktails.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.pojo.Drink
import com.example.cocktails.pojo.DrinkList
import com.example.cocktails.pojo.DrinksByCategory
import com.example.cocktails.pojo.DrinksByCategoryList
import com.example.cocktails.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewModel : ViewModel() {
    private var randomDrinkLiveData = MutableLiveData<Drink>()
    private var popularItemsLiveData = MutableLiveData<List<DrinksByCategory>>()
    fun getRandomDrink() {
        RetrofitInstance.api.getRandomDrink().enqueue(object : Callback<DrinkList> {
            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                if (response.body() != null) {
                    val randomDrink: Drink = response.body()!!.drinks[0]
                    randomDrinkLiveData.value = randomDrink
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                Log.d("FirstFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems() {
        RetrofitInstance.api.getPopularItems("Ordinary_Drink")
            .enqueue(object : Callback<DrinksByCategoryList> {
                override fun onResponse(
                    call: Call<DrinksByCategoryList>,
                    response: Response<DrinksByCategoryList>
                ) {
                    if (response.body() != null) {
                        popularItemsLiveData.value = response.body()!!.drinks
                    }
                }

                override fun onFailure(call: Call<DrinksByCategoryList>, t: Throwable) {
                    Log.d("FirstFragment", t.message.toString())
                }
            })
    }

    fun observeRandomDrinkLiveData(): LiveData<Drink> {
        return randomDrinkLiveData
    }

    fun observePopularDrinkLiveData(): LiveData<List<DrinksByCategory>> {
        return popularItemsLiveData
    }
}