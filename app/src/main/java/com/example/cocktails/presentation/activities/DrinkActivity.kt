package com.example.cocktails.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.databinding.ActivityDrinkBinding
import com.example.cocktails.model.DrinkActivityViewModel
import com.example.cocktails.pojo.Drink
import com.example.cocktails.presentation.fragments.FirstFragment

class DrinkActivity : AppCompatActivity() {
    private lateinit var drinkId: String
    private lateinit var drinkName: String
    private lateinit var drinkThumb: String
    private lateinit var binding: ActivityDrinkBinding
    private lateinit var drinkMvvm: DrinkActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drinkMvvm = ViewModelProviders.of(this)[DrinkActivityViewModel::class.java]

        getDrinkInformationFromIntent()

        setInformationInViews()

        loadingCase()
        drinkMvvm.getDrinkDetail(drinkId)
        observerDrinkDetailsLiveData()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun observerDrinkDetailsLiveData() {
        drinkMvvm.observeDrinkDetailsLiveData().observe(this, object : Observer<Drink> {
            override fun onChanged(value: Drink) {
                onResponseCase()
                val drink = value
                binding.tvCategory.text = "Category: ${drink!!.strCategory}"
                binding.tvAlco.text = "Alcoholic: ${drink!!.strAlcoholic}"
                binding.tvInstructionSteps.text = drink.strInstructions
                binding.tvIngridients.text = drink.strIngredient1
                binding.tvIngridients2.text = drink.strIngredient2
            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(drinkThumb)
            .into(binding.imgDrinkDetail)
        binding.collapsingToolbar.title = drinkName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getDrinkInformationFromIntent() {
        val intent = intent
        drinkId = intent.getStringExtra(FirstFragment.DRINK_ID)!!
        drinkName = intent.getStringExtra(FirstFragment.DRINK_NAME)!!
        drinkThumb = intent.getStringExtra(FirstFragment.DRINK_THUMB)!!

    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddFav.visibility = View.INVISIBLE
        binding.tvInstruction.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvAlco.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddFav.visibility = View.VISIBLE
        binding.tvInstruction.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvAlco.visibility = View.VISIBLE
    }
}