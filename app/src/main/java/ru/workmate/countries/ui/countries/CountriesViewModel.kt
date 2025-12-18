package ru.workmate.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.workmate.countries.domain.usecases.GetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val getCountries: GetCountriesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CountriesUiState>(CountriesUiState.Loading)
    val state = _state.asStateFlow()

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val result = getCountries()
                _state.value = CountriesUiState.Success(result)
            } catch (e: Exception) {
                _state.value = CountriesUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    init {
        fetchCountries()
    }
}