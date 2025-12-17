package com.example.workmatecountries.ui.countries

import com.example.workmatecountries.domain.models.Country

sealed class CountriesUiState {
    object Loading : CountriesUiState()
    data class Success(val countries: List<Country>) : CountriesUiState()
    data class Error(val message: String) : CountriesUiState()
}