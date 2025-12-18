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
import com.example.workmatecountries.data.CountriesDtoRepository
import com.example.workmatecountries.data.CountriesEntityRepository
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
                 * TODO viewModel selected Создается здесь и передается на два экрана, возможно лучше сделать по другому (через hilt или Navigation Compose).
                 * Решение:
                 * Экран 1 (Список): Пользователь нажимает на страну.
                 * Navigation: Срабатывает навигация (это делает View или специальный Router/Coordinator), вы переходите на второй экран, передавая ID страны.
                 * Экран 2 (Детали):
                 * ViewModel второго экрана при создании берет этот ID.
                 * Вызывает GetCountryDetailsUseCase(id).
                 * UseCase идет в Repository.
                 * Данные возвращаются и отображаются
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
                        CountriesScreen(
                            GetCountriesUseCase(CountriesDtoRepository(), //TODO Инъекция зависимостей
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