package ru.practicum.android.diploma.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.state.FavoriteVacanciesState

class FavoriteViewModel : ViewModel() {
//  private val getVacancyFromDbUseCase: GetVacancyFromDbUseCase

    private val _state = MutableLiveData<FavoriteVacanciesState>()
    val state: LiveData<FavoriteVacanciesState> get() = _state

    fun getVacancyList() {
        viewModelScope.launch {
//            val result = getVacancyFromDbUseCase.execute()
//            val favoriteVacanciesState: FavoriteVacanciesState =
//                when (result.first) {
//                    null -> {
//                        if (result.second == NOT_FOUND_CODE.toString()) {
//                            FavoriteVacanciesState.Empty
//                        } else {
//                            FavoriteVacanciesState.Error
//                        }
//                    }
//                    else -> FavoriteVacanciesState.Data(result.first!!)
//                }
//            _state.postValue(favoriteVacanciesState)
        }
    }
}
