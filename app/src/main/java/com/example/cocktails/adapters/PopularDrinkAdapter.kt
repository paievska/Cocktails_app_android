package com.example.cocktails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktails.databinding.PopularItemsBinding
import com.example.cocktails.pojo.DrinksByCategory

class PopularDrinkAdapter : RecyclerView.Adapter<PopularDrinkAdapter.PopularDrinkViewHolder>() {
    lateinit var onItemClick: ((DrinksByCategory) -> Unit)
    private var drinksList = ArrayList<DrinksByCategory>()

    fun setDrinks(drinksList: ArrayList<DrinksByCategory>) {
        this.drinksList = drinksList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularDrinkViewHolder {
        return PopularDrinkViewHolder(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularDrinkViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(drinksList[position].strDrinkThumb)
            .into(holder.binding.imgPopularDrinkItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(drinksList[position])
        }
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    class PopularDrinkViewHolder(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}