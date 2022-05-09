package com.nimtego.plectrum_compose.presentation.common.navigation

internal class CommandBuffer : NavigatorHolder {
    private var navigator: Navigator? = null
    private val pendingCommands = mutableListOf<Array<out Command>>()

    override fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
//        pendingCommands.forEach { navigator.applyCommands(it) }
//        pendingCommands.clear()
    }

    override fun removeNavigator() {
        navigator = null
    }

    fun executeCommands(screen: ScreenDestination) {
        navigator?.navigateToRoute(screen)
    }
}