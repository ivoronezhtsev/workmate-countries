package com.example.workmatecountries.ui

import com.example.workmatecountries.domain.models.Country

sealed class CountriesUiState {
    object Loading : CountriesUiState()                // экран загружается
    data class Success(val countries: List<Country>) : CountriesUiState() // данные загружены
    data class Error(val message: String) : CountriesUiState()           // произошла ошибка
}
