package com.example.workmatecountries.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.data.Country
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {

    private val repository = CountriesRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val result = repository.getAllCountries()
                _countries.postValue(result)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Unknown Error")
            }
        }
    }
}