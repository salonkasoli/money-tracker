package com.github.salonkasoli.moneytracker.util

import android.view.View
import androidx.core.view.isVisible
import com.github.salonkasoli.moneytracker.R

class StateController(
    val content: View,
    val loadingContainer: View?
) {

    fun setState(state: State) {
        when (state) {
            State.Empty -> {
                content.isVisible = true
                loadingContainer?.isVisible = false
            }
            State.Loading -> {
                content.isVisible = false
                loadingContainer?.isVisible = true
            }
            is State.Error -> {
                // TODO
                content.isVisible = true
                loadingContainer?.isVisible = false
            }
            is State.Content -> {
                content.isVisible = true
                loadingContainer?.isVisible = false
            }
        }
    }

    companion object {
        fun default(container: View) : StateController {
            return StateController(
                container.findViewById(R.id.content),
                container.findViewById(R.id.loading_container)
            )
        }
    }
}