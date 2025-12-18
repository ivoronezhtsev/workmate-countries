package ru.workmate.countries.data

import android.content.Context

class CountriesEntityRepository(context: Context) {
    private val db = DatabaseModule.getDatabase(context)
    private val countryDao = db.countryDao()
    fun getCountries() = countryDao.getAllCountries()

    suspend fun saveCountries(countries: List<CountryEntity>) =
        countryDao.insertCountries(countries) //TODO suspend функция, а getCountries нет
}