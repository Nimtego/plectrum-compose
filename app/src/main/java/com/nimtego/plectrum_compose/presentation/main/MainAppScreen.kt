package com.nimtego.plectrum_compose.presentation.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nimtego.plectrum_compose.presentation.main.tabs.FavoritesTab
import com.nimtego.plectrum_compose.presentation.main.tabs.HomeTab
import com.nimtego.plectrum_compose.presentation.main.tabs.ProfileTab
import com.nimtego.plectrum_compose.presentation.splash.SplashScreen
import com.nimtego.plectrum_compose.ui.theme.ComposeTheme
import com.nimtego.plectrum_compose.ui.theme.MainColor

object MainAppScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        SideEffect {
            with(systemUiController) {
                setSystemBarsColor(color = MainColor, darkIcons = useDarkIcons)
                setSystemBarsColor(color = MainColor)
            }
        }

        TabNavigator(HomeTab) { tabNavigator ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = MainColor,
                        title = {
                            Text(
                                text = tabNavigator.current.options.title,
                                color = Color.White
                            )
                        },
                    )
                },
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = MainColor
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(FavoritesTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ComposeTheme {
        SplashScreen.Content()
    }
}