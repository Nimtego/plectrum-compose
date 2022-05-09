package com.nimtego.plectrum_compose.presentation.common.navigation

open class Router : BaseRouter() {
    fun navigateTo(screen: ScreenDestination) {
        executeCommands(screen)
    }
}