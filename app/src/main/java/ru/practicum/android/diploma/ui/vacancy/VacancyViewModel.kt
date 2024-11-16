package ru.practicum.android.diploma.ui.vacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient.Companion.NOT_FOUND_CODE
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.domain.state.VacancyDetailsState
import ru.practicum.android.diploma.domain.state.VacancyDetailsState.VacancyDetailsList
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailsUseCase

class VacancyViewModel(
    private val getDetailsUseCase: GetVacancyDetailsUseCase,
    private val vacancy: VacancyDetails?
) : ViewModel() {

    private val _state = MutableLiveData<VacancyDetailsState>()
    val state: LiveData<VacancyDetailsState> get() = _state

    init {
        _state.value = VacancyDetailsState(VacancyDetailsList.Loading)
        if (vacancy == null) VacancyDetailsList.Empty else getDetails()
    }

    private fun getDetails() {
        viewModelScope.launch {
            val result = getDetailsUseCase.execute(vacancy!!.id)
            val vacancyDetailsState: VacancyDetailsList =
                when (result.first) {
                    null -> {
                        if (result.second == NOT_FOUND_CODE.toString()) {
                            VacancyDetailsList.Empty
                        } else {
                            VacancyDetailsList.Error
                        }
                    }

                    else -> VacancyDetailsList.Data(result.first!!)
                }
            _state.postValue(VacancyDetailsState(vacancyDetailsState))
        }
    }
}
