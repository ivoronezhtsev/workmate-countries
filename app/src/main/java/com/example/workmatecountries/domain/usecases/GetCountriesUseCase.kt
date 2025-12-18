package com.example.workmatecountries.domain.usecases

import com.example.workmatecountries.data.CountriesDtoRepository
import com.example.workmatecountries.data.CountriesEntityRepository
import com.example.workmatecountries.data.toDomain
import com.example.workmatecountries.data.toEntity
import com.example.workmatecountries.domain.models.Country
import kotlinx.coroutines.flow.first

class GetCountriesUseCase(
    private val countriesDtoRepository: CountriesDtoRepository,
    private val countriesEntityRepository: CountriesEntityRepository,
) {
    suspend operator fun invoke(): List<Country> {
        val localData = countriesEntityRepository.getCountries().first()
        if (localData.isNotEmpty()) {
            return localData.map { Country(it.name, it.region, it.population) }
        } else {
            val remoteDto = countriesDtoRepository.getCountries()
            val entities = remoteDto.map { it.toEntity() }
            countriesEntityRepository.saveCountries(entities)
            return entities.map { it. toDomain()}
        }
    }
}
