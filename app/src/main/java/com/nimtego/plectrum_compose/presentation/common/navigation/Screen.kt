package com.nimtego.plectrum_compose.presentation.common.navigation

interface Screen {
    val screenKey: String get() = this::class.java.name
}