package com.dag.nexnft.feature.generator.repository.imagegenerator

import retrofit2.Response


interface ApiSourceImageGeneration {
    suspend fun generateImage(text:String): Response<String>
}