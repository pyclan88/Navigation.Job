package ru.practicum.android.diploma.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.state.VacancyState
import ru.practicum.android.diploma.domain.state.VacancyState.Input
import ru.practicum.android.diploma.domain.state.VacancyState.VacanciesList
import ru.practicum.android.diploma.domain.usecase.GetVacanciesUseCase
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<VacancyState>()
    val state: LiveData<VacancyState> get() = _state

    private val searchDebounceAction = debounce<String>(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText -> search(changedText) }

    fun clearSearch() = _state.postValue(VacancyState(Input.Empty, VacanciesList.Empty))

    fun search(expression: String) = viewModelScope.launch {
        val inputState = Input.Text(expression)
        _state.postValue(VacancyState(inputState, VacanciesList.Loading))

        val result = getVacanciesUseCase.execute(expression, page = 1)

        val vacanciesState: VacanciesList =
            when (result.first) {
                null -> {
                    if (result.second == RetrofitNetworkClient.FAILED_INTERNET_CONNECTION_CODE.toString()) {
                        VacanciesList.NoInternet
                    } else {
                        VacanciesList.Error
                    }
                }

                emptyList<Vacancy>() -> {
                    VacanciesList.Error
                }

                else -> VacanciesList.Data(result.first!!)
            }
        _state.postValue(VacancyState(inputState, vacanciesState))
    }

    fun searchDebounce(expression: String) {
        if (expression.isBlank()) return
        searchDebounceAction(expression)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2_000L
    }
}
