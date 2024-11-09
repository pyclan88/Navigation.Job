package ru.practicum.android.diploma.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentTeamBinding
import ru.practicum.android.diploma.util.BindingFragment

class TeamFragment : BindingFragment<FragmentTeamBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamBinding {
        return FragmentTeamBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contributors = resources.getStringArray(R.array.contributors).toList().shuffled()

        listOf(
            binding.contributor0,
            binding.contributor1,
            binding.contributor2,
            binding.contributor3,
            binding.contributor4
        ).forEachIndexed { index, textView ->
            textView.text = contributors.getOrNull(index) ?: ""
        }
    }
}
