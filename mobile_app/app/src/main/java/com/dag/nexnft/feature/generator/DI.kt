package com.dag.nexnft.feature.generator

import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

var generatorDI = module {
    viewModel { GeneratorVM(get(),get()) }
}