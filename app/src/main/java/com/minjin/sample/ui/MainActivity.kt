package com.minjin.sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.minjin.sample.ui.screen.InboxScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        emailOnCreate(circuit)
    }
}

fun MainActivity.emailOnCreate(circuit: Circuit) {
    setContent {
        MaterialTheme {
            val backStack = rememberSaveableBackStack(InboxScreen)
            val navigator = rememberCircuitNavigator(backStack)

            CircuitCompositionLocals(circuit) {
                NavigableCircuitContent(
                    navigator = navigator,
                    backStack = backStack,
                )
            }
        }
    }
}