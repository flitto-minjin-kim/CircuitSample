package com.minjin.sample.ui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.minjin.sample.data.EmailRepository
import com.minjin.sample.model.Email
import com.minjin.sample.ui.screen.DetailScreen
import com.minjin.sample.ui.screen.InboxScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.produceRetainedState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class InboxPresenter @AssistedInject constructor(
    private val emailRepository: EmailRepository,
    @Assisted private val navigator: Navigator,
) : Presenter<InboxScreen.State> {

    @Composable
    override fun present(): InboxScreen.State {
        val emails by produceRetainedState<List<Email>>(initialValue = emptyList()) {
            value = emailRepository.getEmails()
        }

        return InboxScreen.State(emails) { event ->
            when (event) {
                is InboxScreen.Event.OnClickEmail -> navigator.goTo(DetailScreen(event.emailId))
            }
        }
    }

    @CircuitInject(InboxScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): InboxPresenter
    }
}
