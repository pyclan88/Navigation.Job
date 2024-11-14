package ru.practicum.android.diploma.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.common.ImageAndTextHelperImpl
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.domain.state.VacancyState
import ru.practicum.android.diploma.domain.state.VacancyState.VacanciesList.Data
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.ImageAndTextHelper
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

class SearchFragment : BindingFragment<FragmentSearchBinding>() {

    private val vacanciesAdapter = VacanciesAdapter()
    private val viewModel: SearchViewModel by viewModel()
    private val imageAndTextHelper: ImageAndTextHelper by inject()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecycler()
        configureSearchInput()

        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: VacancyState) {
        when (state.vacanciesList) {
            is VacancyState.VacanciesList.Empty -> with(binding) {
                rvVacancies.invisible()
                pbSearch.invisible()
                ivLookingForPlaceholder.invisible()
                groupPlaceholder.visible()
                imageAndTextHelper.setImageAndText(
                    requireContext(),
                    binding.ivPlaceholder,
                    binding.tvPlaceholder,
                    R.drawable.placeholder_no_vacancy_list_or_region_plate_cat,
                    resources.getString(R.string.no_vacancy_list)
                )
                groupPlaceholder.visible()
            }

            is VacancyState.VacanciesList.NoInternet -> {}
            is VacancyState.VacanciesList.Loading -> with(binding) {
                pbSearch.visible()
                rvVacancies.invisible()
                ivLookingForPlaceholder.invisible()
                groupPlaceholder.invisible()
            }

            is VacancyState.VacanciesList.Error -> {}

            is Data -> with(binding) {
                rvVacancies.visible()
                pbSearch.invisible()
                ivLookingForPlaceholder.invisible()
                vacanciesAdapter.setVacancies(state.vacanciesList.vacancies)
                groupPlaceholder.invisible()
            }
        }
    }

    private fun configureSearchInput() = binding.etSearch.doOnTextChanged { text, _, _, _ ->
        text?.let { viewModel.searchDebounce(it.toString()) }
    }

    private fun configureRecycler() {
        binding.rvVacancies.adapter = vacanciesAdapter
    }
}
