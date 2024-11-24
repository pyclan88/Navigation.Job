package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.common.AppConstants.EMPTY_PARAM_VALUE
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.domain.usecase.filters.GetFiltersUseCase
import ru.practicum.android.diploma.domain.usecase.filters.SetFiltersUseCase
import ru.practicum.android.diploma.util.BindingFragment

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {
    private val setFiltersUseCase: SetFiltersUseCase by inject()
    private val getFiltersUseCase: GetFiltersUseCase by inject()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFilterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureWorkButton()
        configureIndustryButton()
        setFiltersUseCase.execute(mutableListOf("40", "", "", "true"))
        val result = getFiltersUseCase.execute()
        println("filters:$result")
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureWorkButton() = binding.tlPlaceWorkLayout.setEndIconOnClickListener {
        when (binding.tlPlaceWorkLayout.editText?.text.isNullOrEmpty()) {
            true -> showContent(binding.tlPlaceWorkLayout, "Проверка кода")
            else -> showContent(binding.tlPlaceWorkLayout, EMPTY_PARAM_VALUE)
        }
    }

    private fun configureIndustryButton() = binding.tlBranchLayout.setEndIconOnClickListener {
        when (binding.tlBranchLayout.editText?.text.isNullOrEmpty()) {
            true -> showContent(binding.tlBranchLayout, "Проверка кода")
            else -> showContent(binding.tlBranchLayout, EMPTY_PARAM_VALUE)
        }
    }

    private fun showContent(layout: TextInputLayout, content: String) {
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

