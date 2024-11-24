package ru.practicum.android.diploma.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocationBinding
import ru.practicum.android.diploma.domain.state.FieldState
import ru.practicum.android.diploma.util.BindingFragment

class LocationFragment : BindingFragment<FragmentLocationBinding>() {

    private val viewModel: LocationViewModel by viewModel()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLocationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBackButton()
        setupListeners()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                render(state)
            }
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun setupListeners() {
        setupFieldListeners(
            binding.tiCountry
        ) { Toast.makeText(requireContext(), "Нужно сделать переход на выбор страны", Toast.LENGTH_SHORT).show() }

        setupFieldListeners(
            binding.tiRegion
        ) { Toast.makeText(requireContext(), "Нужно сделать переход на выбор региона", Toast.LENGTH_SHORT).show() }
    }

    private fun setupFieldListeners(
        layout: TextInputLayout,
        onClickEmpty: () -> Unit
    ) {
        layout.editText?.setOnClickListener {
            if (layout.editText?.text.isNullOrEmpty()) onClickEmpty()
        }

        layout.setEndIconOnClickListener {
            if (layout.editText?.text?.isNotEmpty()!!) renderField(layout, null)
        }
    }

    private fun render(state: FieldState) {
        renderField(binding.tiCountry, state.upperField)
        renderField(binding.tiRegion, state.lowerField)
    }

    private fun renderField(layout: TextInputLayout, content: String?) {
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
