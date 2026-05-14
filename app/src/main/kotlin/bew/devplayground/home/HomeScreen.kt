// WHAT:
// The home screen — lists all experiments from the registry.
// Calls onNavigate with the chosen Route when an entry is tapped.

package bew.devplayground.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bew.devplayground.experiments.ExperimentEntry
import bew.devplayground.experiments.experiments
import bew.devplayground.nav.Route

@Composable
fun HomeScreen(onNavigate: (Route) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("My Experiments", style = MaterialTheme.typography.headlineMedium)

        // LazyColumn only composes visible items — efficient for lists.
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.fillMaxSize().padding(top = 12.dp),
        ) {
            items(experiments) { entry ->
                ExperimentRow(entry, onClick = { onNavigate(entry.route) })
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ExperimentRow(
    entry: ExperimentEntry,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 12.dp),
    ) {
        Text(entry.title, style = MaterialTheme.typography.titleMedium)
        Text(entry.description, style = MaterialTheme.typography.bodySmall)
    }
}
