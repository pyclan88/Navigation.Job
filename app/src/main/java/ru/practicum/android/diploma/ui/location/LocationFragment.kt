package ru.practicum.android.diploma.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocationBinding
import ru.practicum.android.diploma.domain.state.LocationState
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.invisible
import ru.practicum.android.diploma.util.visible

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
            viewModel.state.collect { state -> render(state) }
        }

        viewModel.getFilters()

        binding.cbApplyButton.setOnClickListener {
            val isCountryFilled = !binding.tiCountry.editText?.text.isNullOrEmpty()
            val isRegionFilled = !binding.tiRegion.editText?.text.isNullOrEmpty()
            viewModel.setFilter(isCountryFilled, isRegionFilled)
            findNavController().popBackStack()
        }
    }

    private fun configureBackButton() =
        binding.tbHeader.setNavigationOnClickListener { findNavController().popBackStack() }

    private fun setupListeners() {
        setupFieldListeners(
            binding.tiCountry
        ) { findNavController().navigate(R.id.action_location_fragment_to_countryFragment) }

        setupFieldListeners(
            binding.tiRegion
        ) { findNavController().navigate(R.id.action_location_fragment_to_regionFragment) }
    }

    private fun setupFieldListeners(
        view: TextInputLayout,
        onClickEmptyField: () -> Unit
    ) {
        view.editText?.setOnClickListener {
            if (view.editText?.text.isNullOrEmpty()) {
                onClickEmptyField()
            }
        }

        view.setEndIconOnClickListener {
            if (view.editText?.text.isNullOrEmpty()) {
                onClickEmptyField()
            } else {
                renderField(view = view, text = null)
            }
        }
    }

    private fun renderField(view: TextInputLayout, text: String?) {
        view.editText?.setText(text)
        if (binding.tiCountry.editText?.text.isNullOrEmpty() &&
            binding.tiRegion.editText?.text.isNullOrEmpty()
        ) {
            viewModel.clearState()
        }
        when {
            view.editText?.text.isNullOrEmpty() -> {
                view.setEndIconDrawable(R.drawable.ic_arrow_forward)
                view.defaultHintTextColor = requireContext().getColorStateList(R.color.dark_grey)
            }

            else -> {
                view.setEndIconDrawable(R.drawable.ic_close)
                view.defaultHintTextColor = requireContext().getColorStateList(R.color.text_color_hint_selection)
            }
        }
    }

    private fun render(state: LocationState) {
        when (state) {
            is LocationState.Data -> with(binding) {
                cbApplyButton.visible()
                renderField(view = tiCountry, text = state.country?.name)
                renderField(view = tiRegion, text = state.region?.name)
            }

            is LocationState.Empty -> with(binding) {
                cbApplyButton.invisible()
            }
        }
    }
}
