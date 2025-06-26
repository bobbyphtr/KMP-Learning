package com.petros.efthymiou.dailypulse.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.petros.efthymiou.dailypulse.source.application.Source
import com.petros.efthymiou.dailypulse.source.presentation.SourcesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    onUpButtonClick: () -> Unit,
    sourcesViewModel: SourcesViewModel = getViewModel()
) {
    Column {
        Toolbar(onUpButtonClick)
        ContentView(sourcesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    onUpButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button",
                )
            }
        }
    )
}

@Composable
private fun ContentView(viewModel: SourcesViewModel) {
    SwipeRefresh(state = SwipeRefreshState(viewModel.sourceState.value.isLoading),
        onRefresh = { viewModel.getSources(true) }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sourceState.value.sources) { source ->
                RowView(source = source)
            }
        }
    }
}

@Composable
private fun RowView(
    source: Source
) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = source.sourceName,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
        )
        Text(
            text = source.sourceDescription,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        Row {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = "${source.sourceCountry} - ${source.sourceLanguage}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}