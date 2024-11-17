package ru.practicum.android.diploma.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.VacancyListState

class FavoriteViewModel : ViewModel() {
//  private val getVacancyFromDbUseCase: GetVacancyFromDbUseCase

    private val _state = MutableLiveData<VacancyListState>()
    val state: LiveData<VacancyListState> get() = _state

    fun getVacancyList() {
        viewModelScope.launch {
//            val result = getVacancyFromDbUseCase.execute()
//            val vacancyList: VacancyListState =
//                when (result.first) {
//                    null -> {
//                        if (result.second == NOT_FOUND_CODE.toString()) {
//                            VacancyListState.Empty
//                        } else {
//                            VacancyListState.Error
//                        }
//                    }
//                    else -> VacancyDetailsList.Data(result.first!!)
//                }
//            _state.postValue(favoriteState)
        }
    }
}
