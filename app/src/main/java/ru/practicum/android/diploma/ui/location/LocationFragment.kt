package ru.practicum.android.diploma.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocationBinding
import ru.practicum.android.diploma.domain.state.FieldState
import ru.practicum.android.diploma.domain.state.FieldState.Field.*
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

        setupListeners()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                render(state)
            }
        }
    }

    private fun setupListeners() {
        binding.incEmptyCountry.root.setOnClickListener {
            Toast.makeText(requireContext(), "Empty Country clicked!", Toast.LENGTH_SHORT).show()
        }
        binding.incEmptyRegion.root.setOnClickListener {
            Toast.makeText(requireContext(), "Empty Region clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.incFilledCountry.ivIcon.setOnClickListener {
            viewModel.clearField(true)
        }

        binding.incFilledRegion.ivIcon.setOnClickListener {
            viewModel.clearField(false)
        }
    }

    private fun render(state: FieldState) {
        when (state.upperField) {
            is Empty -> showEmptyCountry()
            is Filled -> showFilledCountry(state.upperField.data)
        }

        when (state.lowerField) {
            is Empty -> showEmptyRegion()
            is Filled -> showFilledRegion(state.lowerField.data)
        }
    }

    private fun showEmptyCountry() {
        binding.incFilledCountry.root.invisible()
        binding.incEmptyCountry.root.visible()
        binding.incEmptyCountry.tvField.text = resources.getString(R.string.country)
    }

    private fun showFilledCountry(data: String) {
        binding.incEmptyCountry.root.invisible()
        binding.incFilledCountry.root.visible()
        binding.incFilledCountry.tvField.text = resources.getString(R.string.country)
        binding.incFilledCountry.tvData.text = data

    }

    private fun showEmptyRegion() {
        binding.incFilledRegion.root.invisible()
        binding.incEmptyRegion.root.visible()
        binding.incEmptyRegion.tvField.text = resources.getString(R.string.region)
    }

    private fun showFilledRegion(data: String) {
        binding.incEmptyRegion.root.invisible()
        binding.incFilledRegion.root.visible()
        binding.incFilledRegion.tvField.text = resources.getString(R.string.country)
        binding.incFilledRegion.tvData.text = data
    }
}
