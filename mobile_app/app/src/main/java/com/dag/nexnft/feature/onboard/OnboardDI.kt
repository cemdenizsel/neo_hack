package com.dag.nexnft.feature.onboard

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


var onboardDI = module {
    viewModel { OnboardVM(get()) }
}