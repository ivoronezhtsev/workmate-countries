package ru.workmate.countries.ui.countries

import ru.workmate.countries.domain.models.Country

sealed class CountriesUiState {
    object Loading : CountriesUiState()
    data class Success(val countries: List<Country>) : CountriesUiState()
    data class Error(val message: String) : CountriesUiState()
}