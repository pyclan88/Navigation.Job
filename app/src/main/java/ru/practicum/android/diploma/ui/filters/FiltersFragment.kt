package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.toIntOrNull

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {

    private val viewModel: FiltersViewModel by viewModel()
    private var currentFilter = Filter.empty

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFilterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        configureBackButton()
        configureWorkButton()
        configureIndustryButton()
        configureSalaryInput()
        configureWithoutSalaryButton()
        configureApplyButtonListener()
        configureApplyButtonVisible()
        configureResetButtonListener()
        configureResetButtonVisible()

        viewModel.getCurrentFilter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }
    }

    private fun render(state: FiltersState) {
        when (state) {
            is FiltersState.Empty -> {}
            is FiltersState.Data -> showContent(state.filters, state.buttonApplyState)
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureWorkButton() = with(binding) {
        tlPlaceWorkLayout.editText?.setOnClickListener {
            when (tlPlaceWorkLayout.editText?.text.toString().isEmpty()) {
                true -> findNavController().navigate(R.id.action_filters_fragment_to_location_fragment)
                else -> {
                    viewModel.setEmptyCountry()
                    setViewState(tlPlaceWorkLayout, EMPTY_PARAM_VALUE)
                }
            }
            etBranch.doOnTextChanged { _, _, _, _ ->
                configureResetButtonVisible()
                configureApplyButtonVisible()
            }
        }
    }

    private fun configureIndustryButton() = with(binding) {
        tlBranchLayout.editText?.setOnClickListener {
            when (tlBranchLayout.editText?.text.isNullOrEmpty()) {
                true -> findNavController().navigate(R.id.action_filters_fragment_to_industryFragment)
                else -> {
                    viewModel.setEmptyIndustry()
                    setViewState(tlBranchLayout, EMPTY_PARAM_VALUE)
                }
            }

            etBranch.doOnTextChanged { _, _, _, _ ->
                configureResetButtonVisible()
                configureApplyButtonVisible()
            }
        }
    }

    private fun configureSalaryInput() = with(binding) {
        tiSalaryInputText.doOnTextChanged { _, _, _, _ ->
            configureResetButtonVisible()
            configureApplyButtonVisible()
        }
    }

    private fun configureWithoutSalaryButton() = with(binding) {
        cbWithoutSalaryButton.setOnCheckedChangeListener { _, _ ->
            configureResetButtonVisible()
            configureApplyButtonVisible()
        }
    }

    private fun configureApplyButtonListener() = with(binding) {
        cbApplyButton.setOnClickListener {
            viewModel.setSearchFilters()
//            configureResetButtonVisible()
//            configureApplyButtonVisible()
            findNavController().navigate(R.id.action_filters_fragment_to_search_fragment)
        }
    }

    private fun configureApplyButtonVisible() = with(binding) {
//        val isEmpty = etPlaceWork.text.toString() == (currentFilter.location ?: EMPTY_PARAM_VALUE) &&
//            etBranch.text.toString() == (currentFilter.industry ?: EMPTY_PARAM_VALUE) &&
//            tiSalaryInputText.text.toIntOrNull() == currentFilter.salary &&
//            cbWithoutSalaryButton.isChecked == currentFilter.withoutSalaryButton
//        cbApplyButton.isVisible = !isEmpty
    }


    private fun configureResetButtonVisible() = with(binding) {
        val isEmpty = etPlaceWork.text.isNullOrBlank()
            && etBranch.text.isNullOrBlank()
            && tiSalaryInputText.text.isNullOrBlank()
            && !cbWithoutSalaryButton.isChecked
        tvResetButton.isVisible = !isEmpty
    }

    private fun configureResetButtonListener() = with(binding) {
        tvResetButton.setOnClickListener { viewModel.clearFilters() }
    }

    private fun showContent(filter: Filter, appleButtonState: Boolean) {
        setViewState(binding.tlPlaceWorkLayout, filter.location)
        setViewState(binding.tlBranchLayout, filter.industry?.name)
        binding.tiSalaryInputText.setText(filter.salary?.toString() ?: "")
        binding.cbWithoutSalaryButton.isChecked = filter.withoutSalaryButton

        println("($filter)($)")

        binding.cbApplyButton.isVisible = appleButtonState
    }

    private fun setViewState(layout: TextInputLayout, content: String?) {
        layout.editText?.setText(content)
        when {
            layout.editText?.text.isNullOrEmpty() -> {
                layout.setEndIconDrawable(R.drawable.ic_arrow_forward)
                layout.defaultHintTextColor = requireContext().getColorStateList(R.color.dark_grey)
            }

            else -> {
                layout.setEndIconDrawable(R.drawable.ic_close)
                layout.defaultHintTextColor = requireContext().getColorStateList(R.color.text_color_hint_selection)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        viewModel.clearFilters()
    }
}
