package com.example.workmatecountries.data


class CountriesRepository {
    private val api = RetrofitInstance.api

    suspend fun getCountries(): List<CountryData> {
        return api.getAllCountries()
    }
}
