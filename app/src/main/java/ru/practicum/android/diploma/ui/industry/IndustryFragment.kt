package ru.practicum.android.diploma.ui.industry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentIndustryBinding
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.Data
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.Empty
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.Error
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.Loading
import ru.practicum.android.diploma.domain.state.IndustryState.Industries.NoInternet
import ru.practicum.android.diploma.ui.adapters.FilterAdapter
import ru.practicum.android.diploma.ui.adapters.ItemFilter
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.ImageAndTextHelper
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

class IndustryFragment : BindingFragment<FragmentIndustryBinding>() {

    private val filterAdapter = FilterAdapter()
    private val viewModel: IndustryViewModel by viewModel()
    private val imageAndTextHelper: ImageAndTextHelper by inject()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentIndustryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureIndustriesAdapter()
        configureSearch()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getIndustries()
    }

    private fun render(state: IndustryState) {
        when (state.data) {
            is Data -> {
                showContent(state.data.industries)
            }

            is Empty -> showEmpty()
            is Error -> showError()
            is Loading -> showLoading()
            is NoInternet -> showNoInternet()
        }
    }

    private fun configureBackButton() = binding.tbHeader
        .setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureIndustriesAdapter() = with(binding) {
        rvVacancies.adapter = filterAdapter
        filterAdapter.saveFilterListener = object : FilterAdapter.SaveFilterListener {
            override fun onItemClicked(item: ItemFilter) {
                showSelectIndustry(item.data as Industry)
            }
        }
    }

    private fun showNoInternet() {
        with(binding) {
            rvVacancies.invisible()
            pbSearch.invisible()
            groupSelect.invisible()
            placeholder.visible()
            imageAndTextHelper.setImageAndText(
                requireContext(),
                layoutPlaceholder.ivPlaceholder,
                layoutPlaceholder.tvPlaceholder,
                R.drawable.placeholder_vacancy_search_no_internet_skull,
                resources.getString(R.string.no_internet)
            )
        }
    }

    private fun showLoading() {
        with(binding) {
            pbSearch.visible()
            rvVacancies.invisible()
            groupSelect.invisible()
            placeholder.invisible()
        }
    }

    private fun showEmpty() {
        with(binding) {
            rvVacancies.invisible()
            pbSearch.invisible()
            groupSelect.invisible()
            placeholder.visible()
            imageAndTextHelper.setImageAndText(
                requireContext(),
                layoutPlaceholder.ivPlaceholder,
                layoutPlaceholder.tvPlaceholder,
                R.drawable.placeholder_no_vacancy_list_or_region_plate_cat,
                resources.getString(R.string.no_such_industry)
            )
        }
    }

    private fun showError() {
        with(binding) {
            rvVacancies.invisible()
            groupSelect.invisible()
            pbSearch.invisible()
            placeholder.visible()
            imageAndTextHelper.setImageAndText(
                requireContext(),
                layoutPlaceholder.ivPlaceholder,
                layoutPlaceholder.tvPlaceholder,
                R.drawable.placeholder_vacancy_search_server_error_cry,
                resources.getString(R.string.server_error)
            )
            showToast(R.string.toast_error_has_occurred)
        }
    }

    private fun showSelectIndustry(industry: Industry) {
        with(binding) {
            rvVacancies.invisible()
            pbSearch.invisible()
            placeholder.invisible()
            groupSelect.visible()
            iIndustryItem.rbIndustryButton.text = industry.name
            iIndustryItem.rbIndustryButton.isChecked = true
            configureApplyButton(industry)
        }
    }

    private fun showContent(industryList: List<Industry>) {
        with(binding) {
            rvVacancies.visible()
            pbSearch.invisible()
            groupSelect.invisible()
            filterAdapter.updateIndustries(industryList)
            placeholder.invisible()
        }
    }

    private fun configureSearch() = binding.etSearch.doOnTextChanged { text, _, _, _ ->
        viewModel.searchFilter(text.toString())
    }

    private fun configureApplyButton(industry: Industry) = binding.cbApplyButton.setOnClickListener {
        viewModel.setFilters(industry)
        findNavController().navigateUp()
    }
}
