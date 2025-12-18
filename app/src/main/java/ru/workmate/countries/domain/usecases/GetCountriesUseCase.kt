package ru.workmate.countries.domain.usecases

import ru.workmate.countries.data.CountriesDtoRepository
import ru.workmate.countries.data.CountriesEntityRepository
import ru.workmate.countries.data.toDomain
import ru.workmate.countries.data.toEntity
import ru.workmate.countries.domain.models.Country
import kotlinx.coroutines.flow.first

class GetCountriesUseCase(
    private val countriesDtoRepository: CountriesDtoRepository,
    private val countriesEntityRepository: CountriesEntityRepository,
) {
    suspend operator fun invoke(): List<Country> {
        val localData = countriesEntityRepository.getCountries().first()
        if (localData.isNotEmpty()) {
            return localData.map { it.toDomain() }
        } else {
            val remoteDto = countriesDtoRepository.getCountries()
            val entities = remoteDto.map { it.toEntity() }
            countriesEntityRepository.saveCountries(entities)
            return entities.map { it.toDomain() }
        }
    }
}
