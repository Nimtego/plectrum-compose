package com.nimtego.plectrum_compose.presentation.common.navigation

class ComposeNavigator<T : BaseRouter> private constructor(val router: T) {

    fun getNavigatorHolder(): NavigatorHolder = router.commandBuffer

    companion object {

        @JvmStatic
        fun create() = create(Router())

        @JvmStatic
        fun <T : BaseRouter> create(customRouter: T) = ComposeNavigator(customRouter)
    }
}
