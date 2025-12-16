import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workmatecountries.ui.CountriesViewModel

@Composable
fun CountryScreen(viewModel: CountriesViewModel = viewModel(), modifier: Modifier = Modifier) {
    val countries by viewModel.countries.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    if (error != null) {
        Text(
            text = error ?: "",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .padding(16.dp)
        )
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(countries) { index, country ->
                Text(
                    text = country.name.common,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                if (index < countries.lastIndex) {
                    Divider()
                }
            }
        }
    }
}
