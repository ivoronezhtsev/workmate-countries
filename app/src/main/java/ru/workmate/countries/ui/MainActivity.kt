package ru.workmate.countries.ui

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
import ru.workmate.countries.data.CountriesDtoRepository
import ru.workmate.countries.data.CountriesEntityRepository
import ru.workmate.countries.domain.usecases.GetCountriesUseCase
import ru.workmate.countries.ui.details.CountryViewModel
import ru.workmate.countries.ui.details.DetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                val selected: CountryViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return CountryViewModel() as T
                        }
                    }
                )
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        CountriesScreen(
                            GetCountriesUseCase(CountriesDtoRepository(),
                            CountriesEntityRepository(applicationContext)
                        ), onItemClick = {
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