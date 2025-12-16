package com.example.workmatecountries.domain

import com.example.workmatecountries.domain.models.Country

interface CountryRepository {
    fun getCountries(): List<Country>
}