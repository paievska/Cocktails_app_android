package com.example.cocktails.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.pojo.Drink
import com.example.cocktails.pojo.DrinkList
import com.example.cocktails.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkActivityViewModel : ViewModel() {
    private var drinkDetailsLiveData = MutableLiveData<Drink>()

    fun getDrinkDetail(id: String) {
        RetrofitInstance.api.getDrinkDetails(id).enqueue(object : Callback<DrinkList> {
            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                if (response.body() != null) {
                    drinkDetailsLiveData.value = response.body()!!.drinks[0]
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                Log.d("DrinkActivity", t.message.toString())
            }
        })
    }

    fun observeDrinkDetailsLiveData(): LiveData<Drink> {
        return drinkDetailsLiveData
    }
}