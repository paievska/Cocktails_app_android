package com.example.cocktails.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentSecondBinding
import android.widget.Toast

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        binding.info.setText(R.string.info)
        binding.radioButton1.isChecked = sharedPreferences.getBoolean("option1", false)
        binding.radioButton2.isChecked = sharedPreferences.getBoolean("option2", false)
        binding.radioButton3.isChecked = sharedPreferences.getBoolean("option3", false)
        binding.voteBtn.setOnClickListener {
            with(sharedPreferences.edit()){
                putBoolean("option1", binding.radioButton1.isChecked)
                putBoolean("option2", binding.radioButton2.isChecked)
                putBoolean("option3", binding.radioButton3.isChecked)
                apply()
                Toast.makeText(requireContext(), "Thank you for your feedback! And vote save to preference.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}