package com.example.workmatecountries.ui

import CountriesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.domain.usecases.GetCountriesUseCase
import com.example.workmatecountries.ui.details.DetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        CountriesScreen(GetCountriesUseCase(CountriesRepository()), onItemClick = {
                            navController.navigate("details")
                        })
                    }

                    composable("details") {
                        DetailsScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}