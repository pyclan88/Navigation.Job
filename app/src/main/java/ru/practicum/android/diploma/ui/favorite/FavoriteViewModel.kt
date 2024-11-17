package ru.practicum.android.diploma.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.FavoriteState

class FavoriteViewModel : ViewModel() {
//  private val getVacancyFromDbUseCase: GetVacancyFromDbUseCase

    private val _state = MutableLiveData<FavoriteState>()
    val state: LiveData<FavoriteState> get() = _state

    fun getVacancyList() {
        viewModelScope.launch {
//            val result = getVacancyFromDbUseCase.execute()
//            val favoriteState: FavoriteState =
//                when (result.first) {
//                    null -> {
//                        if (result.second == NOT_FOUND_CODE.toString()) {
//                            VacancyDetailsList.Empty
//                        } else {
//                            VacancyDetailsList.Error
//                        }
//                    }
//                    else -> VacancyDetailsList.Data(result.first!!)
//                }
//            _state.postValue(FavoriteState(favoriteState))
        }
    }
}
