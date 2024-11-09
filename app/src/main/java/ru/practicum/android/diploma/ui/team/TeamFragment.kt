package ru.practicum.android.diploma.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.databinding.FragmentTeamBinding
import ru.practicum.android.diploma.R

class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
