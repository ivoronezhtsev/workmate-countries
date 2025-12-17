package com.example.workmatecountries.ui

import CountriesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.domain.usecases.GetCountriesUseCase
import com.example.workmatecountries.ui.details.CountryViewModel
import com.example.workmatecountries.ui.details.DetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                /**
                 * TODO viewModel selected Создается здесь и передается на два экрана, возможно лучше сделать по другому (через hilt или Navigation Compose)
                 */
                val selected: CountryViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return CountryViewModel() as T
                        }
                    }
                )
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        CountriesScreen(GetCountriesUseCase(CountriesRepository()), onItemClick = {
                            navController.navigate("details")
                        }, selected)
                    }

                    composable("details") {
                        DetailsScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            selected
                        )
                    }
                }
            }
        }
    }
}