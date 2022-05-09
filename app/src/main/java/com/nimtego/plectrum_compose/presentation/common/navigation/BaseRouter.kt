package com.nimtego.plectrum_compose.presentation.common.navigation

abstract class BaseRouter {
    internal val commandBuffer = CommandBuffer()
    protected fun executeCommands(screen: ScreenDestination) {
        commandBuffer.executeCommands(screen)
    }
}