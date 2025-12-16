package com.example.workmatecountries.data

import com.example.workmatecountries.domain.Country
import com.example.workmatecountries.domain.CountryRepository

class CountryRepositoryImpl : CountryRepository {

    override fun getCountries(): List<Country> =
        listOf(
            "Россия", "США", "Канада", "Германия", "Франция",
            "Италия", "Испания", "Китай", "Япония", "Южная Корея",
            "Индия", "Бразилия", "Аргентина", "Мексика", "Австралия",
            "Новая Зеландия", "Великобритания", "Швеция", "Норвегия", "Финляндия"
        ).map { Country(it) }
}
