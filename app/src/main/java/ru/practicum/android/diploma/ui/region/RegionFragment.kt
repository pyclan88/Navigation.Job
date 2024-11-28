package ru.practicum.android.diploma.ui.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentRegionBinding
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.domain.state.RegionState
import ru.practicum.android.diploma.ui.adapters.FilterAdapter
import ru.practicum.android.diploma.ui.adapters.FilterAdapter.SaveFilterListener
import ru.practicum.android.diploma.ui.adapters.ItemFilter
import ru.practicum.android.diploma.util.BindingFragment

class RegionFragment : BindingFragment<FragmentRegionBinding>() {

    private val filterAdapter = FilterAdapter()
    private val viewModel: RegionViewModel by viewModel()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureRegionsAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getRegions()
    }

    private fun render(state: RegionState) {
        when (state.data) {
            is RegionState.Data.Data -> filterAdapter.updateRegions(state.data.regions)
            is RegionState.Data.Loading -> { }
            else -> throw UnsupportedOperationException("Будет позже")
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureRegionsAdapter() = with(binding) {
        rvRegions.adapter = filterAdapter
        filterAdapter.saveFilterListener = object : SaveFilterListener {
            override fun onItemClicked(item: ItemFilter) {
                viewModel.setFilter(item.data as Region)
                findNavController().navigateUp()
            }
        }
    }
}
