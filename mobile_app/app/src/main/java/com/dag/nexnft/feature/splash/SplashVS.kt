package com.dag.nexnft.feature.splash

sealed class SplashVS {
    data class SplashStatus(val status:Boolean): SplashVS()
    data object StatusReading: SplashVS()
}