package ru.practicum.android.diploma.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient.Companion.FAILED_INTERNET_CONNECTION_CODE
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.state.VacancyState
import ru.practicum.android.diploma.domain.state.VacancyState.Input
import ru.practicum.android.diploma.domain.state.VacancyState.VacanciesList
import ru.practicum.android.diploma.domain.usecase.GetVacanciesUseCase
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private var lastExpression = ""
    private var currentPage = 1
    private var maxPage = 0

    private var isNextPageLoading = false

    private val _state = MutableStateFlow(
        VacancyState(
            input = Input.Empty,
            vacanciesList = VacanciesList.Start
        )
    )
    val state: StateFlow<VacancyState> get() = _state

    init {
        _state.value = VacancyState(
            input = Input.Empty,
            vacanciesList = VacanciesList.Start,
        )
    }

    private val searchDebounceAction = debounce<String>(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText ->
        if (changedText != lastExpression) {
            search(changedText)
        }
    }

    fun clearSearch() {
        _state.value = VacancyState(Input.Empty, VacanciesList.Start)
    }

    private fun search(expression: String) {
        // println("search:${expression}")
        lastExpression = expression
        _state.value = VacancyState(Input.Text(expression), VacanciesList.Loading)
        requestToServer(expression)
    }

    private fun requestToServer(expression: String) = viewModelScope.launch {
        isNextPageLoading = true
        val result = getVacanciesUseCase.execute(expression = expression, page = currentPage)
        val resultData = result.first?.items
        val totalVacancyCount = result.first?.found ?: 0
        Log.e("TESTdata", "result:${result.first?.toString()}")
        val vacancyState: VacancyState =
            when (resultData) {
                null -> {
                    if (result.second == FAILED_INTERNET_CONNECTION_CODE.toString()) {
                        state.value.copy(vacanciesList = VacanciesList.NoInternet)
                    } else {
                        state.value.copy(vacanciesList = VacanciesList.Error)
                    }
                }

                emptyList<Vacancy>() -> {
                    state.value.copy(vacanciesList = VacanciesList.Empty)
                }

                else -> {
                    isNextPageLoading = false
                    currentPage += 1
                    result.first?.let { maxPage = it.pages }
                    state.value.copy(vacanciesList = VacanciesList.Data(resultData, totalVacancyCount))

                }
            }
        _state.value = vacancyState
    }

    fun searchDebounce(expression: String) {
        if (expression.isBlank()) return
        searchDebounceAction(expression)
    }

    fun onLastItemReached() {
        if (!isNextPageLoading && currentPage < maxPage) requestToServer(lastExpression)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2_000L
    }
}
