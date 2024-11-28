package ru.practicum.android.diploma.ui.industry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.ui.adapters.FilterAdapter
import ru.practicum.android.diploma.ui.adapters.ItemFilter
import ru.practicum.android.diploma.util.BindingFragment

class IndustryFragment : BindingFragment<FragmentIndustryBinding>() {

    private val filterAdapter = FilterAdapter()
    private val viewModel: IndustryViewModel by viewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentIndustryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureIndustriesAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getIndustries()
    }

    private fun render(state: IndustryState) {
        when (state.data) {
            is IndustryState.Industries.Loading -> showLoading()
            is IndustryState.Industries.Data -> filterAdapter.updateIndustries(state.data.industries)
            else -> throw UnsupportedOperationException("Будет позже")
        }
    }

    private fun configureBackButton() = binding.tbHeader
        .setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureIndustriesAdapter() = with(binding) {
        rvVacancies.adapter = filterAdapter
        filterAdapter.saveFilterListener = object : FilterAdapter.SaveFilterListener {
            override fun onItemClicked(item: ItemFilter) {
                viewModel.setFilters(item.data as Industry)
                findNavController().navigateUp()
            }
        }
    }

    private fun showLoading() {
        println("Добавить обработку")
    }
}
