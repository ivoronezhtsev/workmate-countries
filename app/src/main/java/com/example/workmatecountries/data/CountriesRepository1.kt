package com.example.workmatecountries.data


class CountriesRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllCountries(): List<Country> {
        return api.getAllCountries()
    }
}
