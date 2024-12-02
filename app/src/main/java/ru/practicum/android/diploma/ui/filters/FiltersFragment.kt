package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import ru.practicum.android.diploma.domain.state.FiltersState.Data.Empty
import ru.practicum.android.diploma.domain.state.FiltersState.Data.Payload
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.toIntOrNull
import ru.practicum.android.diploma.util.visible

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {

    private val viewModel: FiltersViewModel by viewModel()

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
        configureWithoutSalaryCheckbox()
        configureApplyButton()
        configureResetButton()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getFilters()
    }

    private fun render(state: FiltersState) {
        when (val dataState = state.data) {
            is Empty -> showEmpty()
            is Payload -> showContent(dataState.filters)
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureWorkButton() = with(binding) {
        tlPlaceWorkLayout.editText?.setOnClickListener {
            when (tlPlaceWorkLayout.editText?.text.toString().isEmpty()) {
                true -> findNavController().navigate(R.id.action_filters_fragment_to_location_fragment)
                else -> {
                    viewModel.clearLocation()
                    setViewState(tlPlaceWorkLayout, EMPTY_PARAM_VALUE)
                }
            }
        }
    }

    private fun configureIndustryButton() = with(binding) {
        tlBranchLayout.editText?.setOnClickListener {
            when (tlBranchLayout.editText?.text.isNullOrEmpty()) {
                true -> findNavController().navigate(R.id.action_filters_fragment_to_industryFragment)
                else -> {
                    viewModel.clearIndustry()
                    setViewState(tlBranchLayout, EMPTY_PARAM_VALUE)
                }
            }
        }
    }

    private fun configureSalaryInput() = with(binding) {
        tiSalaryInputText.doOnTextChanged { text, _, _, _ ->
            viewModel.setSalary(text.toString())
        }
    }

    private fun configureWithoutSalaryCheckbox() = with(binding) {
        cbWithoutSalaryButton.setOnCheckedChangeListener { _, isChecked ->
            val hintColor = if (isChecked) R.color.black else R.color.blue
            tlSalaryLayout.hintTextColor = ContextCompat.getColorStateList(requireContext(), hintColor)
            viewModel.setWithoutSalaryButton(isChecked)
        }
    }

    private fun configureApplyButton() = with(binding) {
        cbApplyButton.setOnClickListener {
            viewModel.saveFilters()
            findNavController().navigate(R.id.action_filters_fragment_to_search_fragment)
        }
    }

    private fun configureResetButton() = with(binding) {
        tvResetButton.setOnClickListener { viewModel.clearFilters() }
    }

    private fun showEmpty() {
        setViewState(binding.tlPlaceWorkLayout, null)
        setViewState(binding.tlBranchLayout, null)
        binding.tiSalaryInputText.text = null
        binding.cbWithoutSalaryButton.isChecked = false
        binding.cbApplyButton.invisible()
        binding.tvResetButton.invisible()
    }

    private fun showContent(state: Filter) = with(binding) {
        setViewState(tlPlaceWorkLayout, state.location?.description)
        setViewState(tlBranchLayout, state.industry?.name)
        if (tiSalaryInputText.text.toIntOrNull() != state.salary) {
            tiSalaryInputText.setText(state.salary?.toString() ?: "")
        }
        cbWithoutSalaryButton.isChecked = state.withoutSalaryButton
        cbApplyButton.visible()
        tvResetButton.visible()
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
