package ru.practicum.android.diploma.ui.vacancy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.VacancyDetailsState.VacancyDetailsList
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.domain.state.VacancyDetailsState
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailsUseCase

class VacancyViewModel(
    private val getDetailsUseCase: GetVacancyDetailsUseCase,
    private val id: String
) : ViewModel() {

    private val _state = MutableLiveData<VacancyDetailsState>()
    val state: LiveData<VacancyDetailsState> get() = _state

    init {
        _state.value = VacancyDetailsState(VacancyDetailsList.Loading)
        getDetails()
    }

    private fun getDetails() {
        viewModelScope.launch {
            val result = getDetailsUseCase.execute(id)

            val vacancyDetailsState: VacancyDetailsList =
                when (result.first) {
                    null -> {
                        VacancyDetailsList.Error
                    }
//  Я хз что там сервер передает если вакансия не найдена или удалена поэтому пока что так
//                    result.first.isEmpty() -> {
//                        VacancyDetailsList.Empty
//                    }

                    else -> VacancyDetailsList.Data(result.first!!)
                }
            _state.postValue(VacancyDetailsState(vacancyDetailsState))
        }
    }
}
