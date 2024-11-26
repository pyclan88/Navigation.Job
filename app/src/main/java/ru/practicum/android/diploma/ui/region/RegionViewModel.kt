package ru.practicum.android.diploma.ui.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.RegionState
import ru.practicum.android.diploma.domain.state.RegionState.Input
import ru.practicum.android.diploma.domain.state.RegionState.Regions

class RegionViewModel : ViewModel() {

    private val _state: MutableStateFlow<RegionState> =
        MutableStateFlow(RegionState(Input.Empty, Regions.Loading))
    val state: StateFlow<RegionState>
        get() = _state

    fun getRegions() = viewModelScope.launch {
//        val regions =
//        val industryState = when {
//            regions.first?.isEmpty() == true -> state.value.copy(regions = Regions.Empty)
//            regions.second?.isNotEmpty() == true -> state.value.copy(regions = Regions.Error)
//            else -> state.value.copy(regions = Regions.Data(regions = regions.first!!))
//        }
//        _state.value = industryState
    }
}
