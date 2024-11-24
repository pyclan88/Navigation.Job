package ru.practicum.android.diploma.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.util.BindingFragment

class FiltersFragment : BindingFragment<FragmentFilterBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFilterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        configureWorkButton()
        configureIndustryButton()

    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun configureWorkButton() = binding.tlPlaceWorkLayout.setEndIconOnClickListener {
        when (binding.tlPlaceWorkLayout.editText?.text.isNullOrEmpty()) {
            true -> showContent(binding.tlPlaceWorkLayout, "Россия, Москва")      //проверка работы
            else -> showContent(binding.tlPlaceWorkLayout, "")
        }
    }

    private fun configureIndustryButton() = binding.tlBranchLayout.setEndIconOnClickListener {
        when (binding.tlBranchLayout.editText?.text.isNullOrEmpty()) {
            true -> showContent(binding.tlBranchLayout, "IT")       //проверка работы
            else -> showContent(binding.tlBranchLayout, "")
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

