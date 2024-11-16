package ru.practicum.android.diploma.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancySearchResult
import ru.practicum.android.diploma.domain.state.VacancyState
import ru.practicum.android.diploma.domain.state.VacancyState.Input
import ru.practicum.android.diploma.domain.state.VacancyState.VacanciesList
import ru.practicum.android.diploma.domain.usecase.GetVacanciesUseCase
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private val vacanciesList = mutableListOf<Vacancy>()
    private var lastExpression = ""

    private var isNextPageLoading = false

    private val _state = MutableLiveData<VacancyState>()
    val state: LiveData<VacancyState> get() = _state

    init {
        _state.value = VacancyState(
            input = Input.Empty,
            vacanciesList = VacanciesList.Empty,
        )
    }

    private val searchDebounceAction = debounce<String>(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText -> search(changedText) }

    fun clearSearch() = _state.postValue(VacancyState(Input.Empty, VacanciesList.Empty))

    private fun search(expression: String) {
        lastExpression = expression
        _state.value = VacancyState(Input.Text(expression), VacanciesList.Loading)
        requestToServer(state.value!!)
    }

    private fun requestToServer(state: VacancyState) = viewModelScope.launch {
        isNextPageLoading = true
        val result: Pair<VacancySearchResult?, String?> =
            getVacanciesUseCase.execute(expression = state.input.toString(), page = state.currentPage)
        val resultData = result.first
        val vacanciesList: VacanciesList =
            when (val loadedVacanciesList = resultData?.items) {
                null -> {
                    if (result.second == RetrofitNetworkClient.FAILED_INTERNET_CONNECTION_CODE.toString()) {
                        VacanciesList.NoInternet
                    } else {
                        VacanciesList.Error
                    }
                }

                emptyList<Vacancy>() -> {
                    if (vacanciesList.isEmpty()) VacanciesList.Empty else VacanciesList.Data(vacanciesList)
                    VacanciesList.Error
                }

                else -> {
                    VacanciesList.Data(loadedVacanciesList).also {
                        isNextPageLoading = false
                        _state.postValue(
                            VacancyState(
                                state.input,
                                state.vacanciesList,
                                state.currentPage++,
                                resultData.pages
                            )
                        )
                        vacanciesList.addAll(loadedVacanciesList)
                    }
                }
            }
        val inputState = Input.Text(state.input.toString())
        _state.postValue(VacancyState(inputState, vacanciesList))
    }

    fun searchDebounce(expression: String) {
        if (expression.isBlank()) return
        searchDebounceAction(expression)
    }

    fun onLastItemReached() {
        if (!isNextPageLoading && state.value?.currentPage!! < state.value?.maxPage!!) search(lastExpression)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2_000L
    }
}
