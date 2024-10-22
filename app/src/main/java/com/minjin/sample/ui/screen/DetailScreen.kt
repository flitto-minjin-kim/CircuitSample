package com.minjin.sample.ui.screen

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minjin.sample.model.Email
import com.minjin.sample.ui.item.EmailDetailContent
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailScreen(val emailId: String) : Screen {
    data class State(val email: Email, val eventSink: (Event) -> Unit) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object OnClickBack : Event
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(DetailScreen::class, ActivityRetainedComponent::class)
@Composable
fun EmailDetail(
    state: DetailScreen.State,
    modifier: Modifier = Modifier,
) {
    val subject by remember { derivedStateOf { state.email.subject } }

    Scaffold(
        modifier = modifier.systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(subject) },
                navigationIcon = {
                    IconButton(onClick = { state.eventSink(DetailScreen.Event.OnClickBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = spacedBy(16.dp)
        ) {
            EmailDetailContent(email = state.email)
        }
    }
}