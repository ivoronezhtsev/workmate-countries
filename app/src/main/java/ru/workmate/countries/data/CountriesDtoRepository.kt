package ru.workmate.countries.data


class CountriesDtoRepository {
    private val api = RetrofitInstance.api

    suspend fun getCountries(): List<CountryData> {
        return api.getAllCountries()
    }
}
