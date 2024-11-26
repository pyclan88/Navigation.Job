package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
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
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.util.BindingFragment

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {

    private val viewModel: FiltersViewModel by viewModel()
    private var filterState: Filter? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFilterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                showContent(state.filters)
                filterState = state.filters
            }
        }
        configureBackButton()
        configureWorkButton()
        configureIndustryButton()
        configureSalaryInput()
        configureWithoutSalaryButton()
        configureApplyButton()
        configureResetButton()
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureWorkButton() = binding.tlPlaceWorkLayout.setEndIconOnClickListener {
        when (binding.tlPlaceWorkLayout.editText?.text.isNullOrEmpty()) {
            true -> findNavController().navigate(R.id.action_filters_fragment_to_location_fragment)
            else -> setViewState(binding.tlPlaceWorkLayout, EMPTY_PARAM_VALUE)
        }
        binding.etBranch.doOnTextChanged { text, _, _, _ ->
            configureResetButton()
            configureApplyButton()
            filterState?.copy(placeWork = text.toString())
        }
    }

    private fun configureIndustryButton() {
        binding.tlBranchLayout.setEndIconOnClickListener {
            when (binding.tlBranchLayout.editText?.text.isNullOrEmpty()) {
                true -> findNavController().navigate(R.id.action_filters_fragment_to_industryFragment)
                else -> setViewState(binding.tlBranchLayout, EMPTY_PARAM_VALUE)
            }

            binding.etBranch.doOnTextChanged { text, _, _, _ ->
                configureResetButton()
                configureApplyButton()
                filterState?.copy(industry = text.toString())
            }
        }
    }

    private fun configureSalaryInput() {
        binding.tiSalaryInputText.doOnTextChanged { text, _, _, _ ->
            configureResetButton()
            configureApplyButton()
            filterState?.copy(salary = text.toString())
        }
    }

    private fun configureWithoutSalaryButton() {
        binding.cbWithoutSalaryButton.setOnCheckedChangeListener { _, isChecked ->
            configureResetButton()
            configureApplyButton()
            filterState?.copy(withoutSalaryButton = isChecked)
        }
    }

    private fun configureApplyButton() {
        binding.cbApplyButton.isVisible = !(
            binding.etPlaceWork.text.toString() == filterState?.placeWork &&
                binding.etBranch.text.toString() == filterState?.industry &&
                binding.tiSalaryInputText.text.toString() == filterState?.salary &&
                binding.cbWithoutSalaryButton.isChecked == filterState?.withoutSalaryButton
            )
        binding.cbApplyButton.setOnClickListener { viewModel.setFilters(filterState) }
    }

    private fun configureResetButton() {
        binding.tvResetButton.isVisible = !(
            binding.etPlaceWork.text.isNullOrEmpty() &&
                binding.etBranch.text.isNullOrEmpty() &&
                binding.tiSalaryInputText.text.isNullOrEmpty() &&
                !binding.cbWithoutSalaryButton.isChecked)
        binding.tvResetButton.setOnClickListener { viewModel.resetFiltersState() }
    }

    private fun showContent(state: Filter) {
        setViewState(binding.tlPlaceWorkLayout, state.placeWork)
        setViewState(binding.tlBranchLayout, state.industry)
        binding.tiSalaryInputText.setText(state.salary)
        binding.cbWithoutSalaryButton.isChecked = state.withoutSalaryButton
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
}
