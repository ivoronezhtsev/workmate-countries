package com.example.workmatecountries.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.workmatecountries.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(onBackClick: () -> Unit, selected: CountryViewModel) {

    val country = selected.selectedCountry.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            country?.let {
                DetailItem(label = stringResource(R.string.continent), value = it.region)
                DetailItem(label = stringResource(R.string.population), value = it.population.toString())
                //DetailItem(label = stringResource(R.string.language), value = "language")
                //DetailItem(label = stringResource(R.string.capital), value = it.region)
                DetailItem(label = stringResource(R.string.currency), value = "currency")
                DetailItem(label = stringResource(R.string.other_details), value = "otherDetails")
            }
        }
    }
}
@Composable
fun DetailItem(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}