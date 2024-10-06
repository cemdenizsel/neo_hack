package com.dag.nexnft.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dag.nexnft.R
import com.dag.nexnft.base.navcontroller.NavScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    splashScreenVM: SplashScreenVM = koinViewModel(),
    navController: NavController
) {
    val state = splashScreenVM.state.collectAsState()

    LaunchedEffect(key1 = state.value) {
        if (state.value is SplashVS.SplashStatus) {
            if ((state.value as SplashVS.SplashStatus).status) {
                navController.navigate(NavScreen.HomeScreen.route)
            } else {
                navController.navigate(NavScreen.Onboard.route)
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        splashScreenVM.getOnboardStatus()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Logo"
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen(
        navController = rememberNavController()
    )
}