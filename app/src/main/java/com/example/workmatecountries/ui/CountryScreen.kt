package com.example.workmatecountries.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workmatecountries.data.CountryRepositoryImpl
import com.example.workmatecountries.domain.CountryRepository

@Composable
fun CountryScreen(
    repository: CountryRepository = CountryRepositoryImpl(),
            modifier: Modifier = Modifier
) {
    val countries = remember {
        repository.getCountries()
    }

    LazyColumn(modifier = modifier) {

        itemsIndexed(countries) { index, country ->

            Text(
                text = country.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp
            )

            if (index < countries.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Countries") }
            )
        }
    ) { paddingValues ->
        CountryScreen(
            modifier = Modifier.padding(paddingValues)
        )
    }
}
