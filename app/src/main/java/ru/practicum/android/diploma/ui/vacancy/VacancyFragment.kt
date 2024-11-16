package ru.practicum.android.diploma.ui.vacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.domain.state.VacancyDetailsState
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.JsonParser

class VacancyFragment : BindingFragment<FragmentVacancyBinding>() {

    private var vacancy: VacancyDetails? = null
    private val jsonParser: JsonParser by inject()
    private val viewModel: VacancyViewModel by viewModel() {
        parametersOf(vacancy)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVacancyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVacancy()
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }
        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun initVacancy() {
        val vacancyJson = requireArguments().getString(VACANCY_DETAILS) ?: ""
        vacancy = jsonParser.getObject(vacancyJson, object : TypeToken<VacancyDetails>() {})
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
        fun createArgs(vacancy: String): Bundle =
            bundleOf(VACANCY_DETAILS to vacancy)
    }
}
