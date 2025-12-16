package com.example.workmatecountries.data

import retrofit2.http.GET

interface RestCountriesApi {
    @GET("all?fields=name,flags,capital,population,currencies,region")
    suspend fun getAllCountries(): List<CountryData>
}
