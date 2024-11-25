package ru.practicum.android.diploma.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentCountryBinding
import ru.practicum.android.diploma.util.BindingFragment

class CountryFragment : BindingFragment<FragmentCountryBinding>() {

    private val viewModel: CountryViewModel by viewModel()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCountryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }
}
