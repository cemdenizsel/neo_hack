package com.dag.nexnft.feature.generator.repository.imagegenerator

import retrofit2.Response
import retrofit2.Retrofit


class ApiServiceImageGenerationImpl(retrofit: Retrofit) : ApiSourceImageGeneration {

    private val apiService: ApiServiceImageGeneration = retrofit.create(ApiServiceImageGeneration::class.java)
    override suspend fun generateImage(text: String): Response<String> {
        return apiService.generateImage(text)
    }

}