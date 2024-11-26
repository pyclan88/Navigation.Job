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
import ru.practicum.android.diploma.domain.state.FiltersState
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {

    private val viewModel: FiltersViewModel by viewModel()
    private lateinit var filterState: Filter

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
    }

    private fun configureIndustryButton() = binding.tlBranchLayout.setEndIconOnClickListener {
        when (binding.tlBranchLayout.editText?.text.isNullOrEmpty()) {
            true -> findNavController().navigate(R.id.action_filters_fragment_to_industryFragment)
            else -> setViewState(binding.tlBranchLayout, EMPTY_PARAM_VALUE)
        }
    }

    private fun configureApplyButton() {
        binding.cbApplyButton.setOnClickListener {
            viewModel.setFilters(filterState)
        }
    }

    private fun configureSalaryInput() {


        binding.tiSalaryInputText.doOnTextChanged { text, _, _, _ ->
            filterState.salary = text.toString()

//            showContent()
//            if (text.isNullOrEmpty()) binding.tvResetButton.invisible() else binding.tvResetButton.visible()
//            if (text == filterState.salary) binding.cbApplyButton.invisible() else {
//                binding.cbApplyButton.visible()
//                filterState.salary = text.toString()
//            }
        }
    }

    private fun configureWithoutSalaryButton() {
        binding.cbWithoutSalaryButton.setOnCheckedChangeListener { _, isChecked ->
            filterState.withoutSalaryButton = isChecked
        }
    }


    private fun configureResetButton() {

    }

    private fun showContent(state: Filter) {
        setViewState(binding.tlPlaceWorkLayout, state.placeWork)
        setViewState(binding.tlBranchLayout, state.industry)
        binding.tiSalaryInputText.setText(state.salary)
        binding.cbWithoutSalaryButton.isChecked = state.withoutSalaryButton

        if (state.placeWork.isNullOrEmpty() &&
            state.industry.isNullOrEmpty() &&
            state.salary.isNullOrEmpty() &&
            !state.withoutSalaryButton
        ) {
            binding.tvResetButton.invisible()
        } else {
            binding.tvResetButton.visible()
//      filterState = state
        }


//        if (state.placeWork == filterState.placeWork &&
//            state.industry == filterState.industry &&
//            state.salary == filterState.salary &&
//            state.withoutSalaryButton == filterState.withoutSalaryButton
//        ) {
//            binding.tvResetButton.invisible()
//        } else {
//            binding.tvResetButton.visible()
////      filterState = state
//        }


//        if (!isChecked) binding.tvResetButton.invisible() else binding.tvResetButton.visible()
//        if (isChecked == filterState.withoutSalaryButton) binding.cbApplyButton.invisible() else {
//            binding.cbApplyButton.visible()
//        }
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
