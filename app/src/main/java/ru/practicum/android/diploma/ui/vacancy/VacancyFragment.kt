package ru.practicum.android.diploma.ui.vacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.domain.state.VacancyDetailsState
import ru.practicum.android.diploma.util.BindingFragment

class VacancyFragment : BindingFragment<FragmentVacancyBinding>() {

    private var vacancyId: String? = ""
    private val viewModel: VacancyViewModel by viewModel() {
        parametersOf(vacancyId)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVacancyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vacancyId = requireArguments().getString(VACANCY_DETAILS)
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }
        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: VacancyDetailsState) {
        when (state.detailsList) {
            is VacancyDetailsState.VacancyDetailsList.Loading -> TODO()
            is VacancyDetailsState.VacancyDetailsList.Empty -> TODO()
            is VacancyDetailsState.VacancyDetailsList.Data -> TODO()
            is VacancyDetailsState.VacancyDetailsList.Error -> TODO()
        }
    }

    companion object {
        private const val VACANCY_DETAILS = "vacancy_details"
        fun createArgs(vacancyId: String): Bundle =
            bundleOf(VACANCY_DETAILS to vacancyId)
    }
}
