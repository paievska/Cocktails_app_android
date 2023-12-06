package com.example.cocktails.presentation.infoPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cocktails.R
import com.example.cocktails.data.locale.SharedPref
import com.example.cocktails.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        sharedPref = SharedPref(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.info.setText(R.string.info)
        binding.radioButton1.isChecked = sharedPref.getOption1()
        binding.radioButton2.isChecked = sharedPref.getOption2()
        binding.radioButton3.isChecked = sharedPref.getOption3()
        binding.voteBtn.setOnClickListener {
            sharedPref.setOption1(binding.radioButton1.isChecked)
            sharedPref.setOption2(binding.radioButton2.isChecked)
            sharedPref.setOption3(binding.radioButton3.isChecked)
            Toast.makeText(
                requireContext(),
                "Thank you for your feedback! And vote save to preference.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}