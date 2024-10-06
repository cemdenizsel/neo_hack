package com.dag.nexnft.feature.splash

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var splashDI = module {
    viewModel { SplashScreenVM(get()) }
}