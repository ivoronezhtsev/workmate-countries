package com.example.workmatecountries.ui

import CountryScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.domain.usecases.GetCountriesUseCase

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CountryScreen(GetCountriesUseCase(CountriesRepository()))
            }
        }
    }
}