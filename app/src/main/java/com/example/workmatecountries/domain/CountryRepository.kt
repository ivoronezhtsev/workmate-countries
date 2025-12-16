package com.example.workmatecountries.domain

interface CountryRepository {
    fun getCountries(): List<Country>
}