package com.example.workmatecountries

import CountryScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workmatecountries.data.CountriesRepository
import com.example.workmatecountries.domain.usecases.GetCountriesUseCase
import com.example.workmatecountries.ui.CountriesViewModel
import com.example.workmatecountries.ui.theme.WorkMateCountriesTheme

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkMateCountriesTheme {
        Greeting("Android")
    }
}

