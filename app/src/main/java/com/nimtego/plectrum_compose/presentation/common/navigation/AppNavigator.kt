package com.nimtego.plectrum_compose.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

open class AppNavigator
constructor(
    protected val navController: NavController
) : Navigator {
    override fun navigateToRoute(screen: ScreenDestination) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}