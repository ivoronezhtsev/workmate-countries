package ru.workmate.countries.data

import ru.workmate.countries.domain.models.Country
import java.util.UUID

data class CountryData(
    val name: Name,
    val flags: Flags,
    val capital: List<String>?,
    val population: Long,
    val currencies: Map<String, Currency>?,
    val region: String
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>?
)

data class NativeName(
    val official: String,
    val common: String
)

data class Flags(
    val png: String,
    val svg: String,
    val alt: String?
)

data class Currency(
    val name: String,
    val symbol: String?
)
fun CountryData.toDomain(): Country {
    return Country(name.common, region, population)
}

fun CountryData.toEntity(): CountryEntity {
    return CountryEntity(UUID.randomUUID().toString(), name.common, region, population)
}
