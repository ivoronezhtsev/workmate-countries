package com.example.workmatecountries.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workmatecountries.domain.models.Country

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val region: String,
    val population: Long
)

fun CountryEntity.toDomain(): Country {
    return Country(name, region, population)
}