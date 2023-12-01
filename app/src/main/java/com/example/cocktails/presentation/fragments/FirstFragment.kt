package com.example.cocktails.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cocktails.adapters.PopularDrinkAdapter
import com.example.cocktails.databinding.FragmentFirstBinding
import com.example.cocktails.model.FirstFragmentViewModel
import com.example.cocktails.pojo.Drink
import com.example.cocktails.pojo.DrinksByCategory
import com.example.cocktails.presentation.activities.DrinkActivity

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var firstMvvm: FirstFragmentViewModel
    private lateinit var randomDrink: Drink
    private lateinit var popularItemsAdapter: PopularDrinkAdapter

    companion object {
        const val DRINK_ID = "com.example.cocktails.presentation.fragments.idDrink"
        const val DRINK_NAME = "com.example.cocktails.presentation.fragments.nameDrink"
        const val DRINK_THUMB = "com.example.cocktails.presentation.fragments.thumbDrink"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstMvvm = ViewModelProviders.of(this)[FirstFragmentViewModel::class.java]

        popularItemsAdapter = PopularDrinkAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemsRecyclerView()
        firstMvvm.getRandomDrink()
        observerRandomDrink()
        onRandomDrinkClick()
        firstMvvm.getPopularItems()
        observerPopularItemsLiveData()

        onPopularItemClick()

    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { drink ->
            val intent = Intent(activity, DrinkActivity::class.java)
            intent.putExtra(DRINK_ID, drink.idDrink)
            intent.putExtra(DRINK_NAME, drink.strDrink)
            intent.putExtra(DRINK_THUMB, drink.strDrinkThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.rvDrinks.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun observerPopularItemsLiveData() {
        firstMvvm.observePopularDrinkLiveData().observe(
            viewLifecycleOwner
        ) { drinkList ->
            popularItemsAdapter.setDrinks(drinksList = drinkList as ArrayList<DrinksByCategory>)
        }
    }

    private fun onRandomDrinkClick() {
        binding.randomDrinksCard.setOnClickListener {
            val intent = Intent(activity, DrinkActivity::class.java)
            intent.putExtra(DRINK_ID, randomDrink.idDrink)
            intent.putExtra(DRINK_NAME, randomDrink.strDrink)
            intent.putExtra(DRINK_THUMB, randomDrink.strDrinkThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomDrink() {
        firstMvvm.observeRandomDrinkLiveData().observe(
            viewLifecycleOwner
        ) { drink ->
            Glide.with(this@FirstFragment)
                .load((drink!!.strDrinkThumb))
                .into(binding.imgRandomDrinks)
            this.randomDrink = drink
        }
    }

}