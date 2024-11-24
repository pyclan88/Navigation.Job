package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.util.BindingFragment

class IndustryFragment : BindingFragment<FragmentIndustryBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentIndustryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        configureBackButton()

    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }
}
