package com.minjin.sample.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minjin.sample.model.Email
import com.minjin.sample.ui.item.EmailItem
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
data object InboxScreen : Screen {
    data class State(
        val emails: List<Email>,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data class OnClickEmail(val emailId: String) : Event()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(InboxScreen::class, ActivityRetainedComponent::class)
@Composable
fun Inbox(
    state: InboxScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("inbox") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(
                items = state.emails,
                key = { email -> email.id }
            ) { email ->
                EmailItem(
                    email = email,
                    onClick = { state.eventSink(InboxScreen.Event.OnClickEmail(email.id)) },
                )
            }
        }
    }
}