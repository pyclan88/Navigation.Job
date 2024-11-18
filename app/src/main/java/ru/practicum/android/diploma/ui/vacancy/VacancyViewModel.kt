package ru.practicum.android.diploma.ui.vacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient.Companion.NOT_FOUND_CODE
import ru.practicum.android.diploma.domain.state.VacancyDetailsState
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailsUseCase

class VacancyViewModel(
    private val getDetailsUseCase: GetVacancyDetailsUseCase,
) : ViewModel() {

    private val _state = MutableLiveData(VacancyDetailsState(VacancyDetailsState.Vacancy.Loading))
    val state: LiveData<VacancyDetailsState> get() = _state

    fun getDetails(id: String) {
        viewModelScope.launch {
            val result = getDetailsUseCase.execute(id)
            val vacancyDetailsState: VacancyDetailsState.Vacancy =
                when (result.first) {
                    null -> if (result.second == NOT_FOUND_CODE.toString()) {
                        VacancyDetailsState.Vacancy.Empty
                    } else {
                        VacancyDetailsState.Vacancy.Error
                    }

                    else -> VacancyDetailsState.Vacancy.Data(result.first!!)
                }
            _state.postValue(VacancyDetailsState(vacancyDetailsState))
        }
    }
}
