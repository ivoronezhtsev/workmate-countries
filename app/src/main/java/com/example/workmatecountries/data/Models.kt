package com.example.workmatecountries.data

data class Country(
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
