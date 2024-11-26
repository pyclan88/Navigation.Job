package ru.practicum.android.diploma.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentCountryBinding
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.state.CountryState
import ru.practicum.android.diploma.ui.adapters.FilterAdapter
import ru.practicum.android.diploma.ui.adapters.FilterAdapter.SaveFilterListener
import ru.practicum.android.diploma.ui.adapters.ItemFilter
import ru.practicum.android.diploma.util.BindingFragment

class CountryFragment : BindingFragment<FragmentCountryBinding>() {

    private val filterAdapter = FilterAdapter()
    private val viewModel: CountryViewModel by viewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCountryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureCountriesAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getCountries()
    }

    private fun render(state: CountryState) {
        when (state) {
            is CountryState.Data -> filterAdapter.updateCountries(state.countries)
            is CountryState.Empty -> {}     // TODO
            is CountryState.Error -> {}     // TODO
            is CountryState.Loading -> {}   // TODO
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureCountriesAdapter() = with(binding) {
        rvCountries.adapter = filterAdapter
        filterAdapter.saveFilterListener = object : SaveFilterListener {
            override fun onItemClicked(item: ItemFilter) {
                viewModel.setFilter(item.data as Country)
                findNavController().navigateUp()
            }
        }
    }
}
