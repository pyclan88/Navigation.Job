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
import ru.practicum.android.diploma.domain.state.CountryState
import ru.practicum.android.diploma.domain.state.IndustryState
import ru.practicum.android.diploma.ui.adapters.FilterAdapter
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.ImageAndTextHelper
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

class IndustryFragment : BindingFragment<FragmentIndustryBinding>() {

    private val viewModel: IndustryViewModel by viewModel()
    private val imageAndTextHelper: ImageAndTextHelper by inject()
    private var filterAdapter: FilterAdapter = FilterAdapter{filterId -> selectIndustry(filterId)}
    private var industryList: List<Industry> = mutableListOf()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentIndustryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureSearch()
        binding.rvVacancies.adapter = filterAdapter

        viewModel.getIndustries()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                render(state.industries)
            }
        }
    }

    private fun render(state: IndustryState.IndustriesList) {
        when (state) {
            is IndustryState.IndustriesList.Data -> {
                showContent(state.industries)
                industryList = state.industries
            }

            IndustryState.IndustriesList.Empty -> {
                showNoResult()
            }

            IndustryState.IndustriesList.Error -> {
                showError()
            }

            IndustryState.IndustriesList.Loading -> {
                showLoading()
            }

            IndustryState.IndustriesList.NoInternet -> {
                showNoInternet()
            }
        }
    }

    private fun configureSearch() = binding.tiSearchInput.doOnTextChanged { text, _, _, _ ->
        val filterList = industryList.filter {
            it.name.lowercase().contains(text.toString().lowercase())
        }
        showContent(filterList)
    }

    private fun showContent(showList: List<Industry>) {
        filterAdapter.updateIndustries(showList)
        with(binding) {
            pbSearch.invisible()
            rvVacancies.visible()
            groupPlaceholder.invisible()
        }
    }

    private fun showNoResult() {
        with(binding) {
            pbSearch.invisible()
            rvVacancies.invisible()
            groupPlaceholder.visible()
        }
    }

    private fun showLoading() {
        with(binding) {
            pbSearch.visible()
            rvVacancies.invisible()
            groupPlaceholder.invisible()
        }
    }

    private fun showNoInternet() {
        if (filterAdapter.itemCount == 0) {
            with(binding) {
                rvVacancies.invisible()
                pbSearch.invisible()
                groupPlaceholder.visible()
                imageAndTextHelper.setImageAndText(
                    requireContext(),
                    ivPlaceholder,
                    tvPlaceholder,
                    R.drawable.placeholder_vacancy_search_no_internet_skull,
                    resources.getString(R.string.no_internet)
                )
            }
        } else {
            showToast(R.string.toast_check_your_internet_connection)
        }
    }

    private fun showError() {
        with(binding) {
            rvVacancies.invisible()
            pbSearch.invisible()
            groupPlaceholder.visible()
            imageAndTextHelper.setImageAndText(
                requireContext(),
                ivPlaceholder,
                tvPlaceholder,
                R.drawable.placeholder_vacancy_search_server_error_cry,
                resources.getString(R.string.server_error)
            )
            showToast(R.string.toast_error_has_occurred)
        }
    }

    private fun selectIndustry(industryId: String){
        val selectIndustry =
        showContent(industryList.filter { it.id == industryId })
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }
}
