package com.dag.nexnft.base.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dag.nexnft.MainActivity

import com.dag.nexnft.base.NextNFTSurface
import com.dag.nexnft.base.appbar.CustomAppbar
import com.dag.nexnft.feature.generator.Generator
import com.dag.nexnft.feature.home.HomeView
import com.dag.nexnft.feature.onboard.OnboardView
import com.dag.nexnft.feature.splash.SplashScreen

@Composable
fun NavGraph(
    startDestination: String = NavScreen.Splash.route,
) {
    val navController = rememberNavController()

    NextNFTSurface(
        appbar = {
            CustomAppbar()
        },
        bottomBar = {

        },
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavScreen.HomeScreen.route) {
                HomeView(
                    navController = navController
                )
            }
            composable(NavScreen.Onboard.route) {
                OnboardView(
                    navigationController = navController
                )
            }
            composable(NavScreen.Generator.route) {
                Generator()
            }
            composable(NavScreen.Splash.route) {
                SplashScreen(
                    navController = navController
                )
            }
        }
    }
}
