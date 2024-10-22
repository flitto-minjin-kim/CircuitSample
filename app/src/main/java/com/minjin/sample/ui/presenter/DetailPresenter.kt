package com.minjin.sample.ui.presenter

import androidx.compose.runtime.Composable
import com.minjin.sample.data.EmailRepository
import com.minjin.sample.ui.screen.DetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent


class DetailPresenter @AssistedInject constructor(
    @Assisted private val screen: DetailScreen,
    // private val emailRepository: EmailRepository,
    @Assisted private val navigator: Navigator,
) : Presenter<DetailScreen.State> {

    @Composable
    override fun present(): DetailScreen.State {
        val emailRepository = EmailRepository()
        val email = emailRepository.getEmail(screen.emailId)

        return DetailScreen.State(email) { event ->
            when (event) {
                is DetailScreen.Event.OnClickBack -> navigator.pop()
            }
        }
    }

    @CircuitInject(DetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            screen: DetailScreen,
            navigator: Navigator
        ): DetailPresenter
    }
}