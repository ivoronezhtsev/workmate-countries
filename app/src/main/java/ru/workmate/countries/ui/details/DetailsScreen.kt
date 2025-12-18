package ru.workmate.countries.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import ru.workmate.countries.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    viewModel: CountryViewModel,
) {
    val country = viewModel.selectedCountry.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.details_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        country?.let { data ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                item {
                    DetailCard(
                        title = stringResource(R.string.continent),
                        value = data.region
                    )
                }

                item {
                    DetailCard(
                        title = stringResource(R.string.population),
                        value = data.population.toString()
                    )
                }

                /*item {
                    DetailCard(
                        title = stringResource(R.string.currency),
                        value = "currency"
                    )
                }

                item {
                    DetailCard(
                        title = stringResource(R.string.other_details),
                        value = "otherDetails"
                    )
                }*/
            }
        }
    }
}