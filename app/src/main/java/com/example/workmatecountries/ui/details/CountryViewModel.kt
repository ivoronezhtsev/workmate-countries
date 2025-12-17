package com.example.workmatecountries.ui.details

import androidx.lifecycle.ViewModel
import com.example.workmatecountries.domain.models.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountryViewModel : ViewModel() {
    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }
}