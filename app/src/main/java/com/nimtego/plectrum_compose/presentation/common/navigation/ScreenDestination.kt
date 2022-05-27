package com.nimtego.plectrum_compose.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import com.nimtego.plectrum_compose.presentation.base.BaseViewModel

abstract class FeatureScreen : ScreenDestination

sealed interface ScreenDestination {

    val route: String get() = this::class.java.name

    @Composable
    fun Content(viewModel: BaseViewModel<*>)

    @Composable
    fun viewModel(): BaseViewModel<*>
    fun getArguments(): List<NamedNavArgument> = listOf()
}
