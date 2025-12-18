import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.workmate.countries.R
import ru.workmate.countries.domain.usecases.GetCountriesUseCase
import ru.workmate.countries.ui.countries.CountriesUiState
import ru.workmate.countries.ui.countries.CountriesViewModel
import ru.workmate.countries.ui.details.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    getCountriesUseCase: GetCountriesUseCase,
    onItemClick: () -> Unit,
    selected: CountryViewModel
) {
    val viewModel: CountriesViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CountriesViewModel(getCountriesUseCase) as T
            }
        }
    )
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is CountriesUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CountriesUiState.Success -> {
                    val countries = (uiState as CountriesUiState.Success).countries

                    LazyColumn {
                        itemsIndexed(countries) { index, country ->
                            Text(
                                text = country.name,
                                modifier = Modifier
                                    .clickable {

                                        selected.selectCountry(country)
                                        onItemClick()

                                    }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )

                            if (index < countries.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    thickness = 1.dp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }

                is CountriesUiState.Error -> {
                    Text(
                        text = (uiState as CountriesUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
