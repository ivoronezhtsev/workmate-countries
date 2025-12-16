package com.example.workmatecountries.domain.usecases

import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.data.toDomain
import com.example.workmatecountries.domain.models.Country

class GetCountriesUseCase(
    private val repository: CountriesRepository
) {
    suspend operator fun invoke(): List<Country> {
        return repository.getCountries().map { it.toDomain() }
    }
}
