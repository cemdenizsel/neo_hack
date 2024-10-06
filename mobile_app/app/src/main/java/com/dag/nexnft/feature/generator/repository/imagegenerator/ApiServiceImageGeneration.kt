package com.dag.nexnft.feature.generator.repository.imagegenerator

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceImageGeneration {

    @GET("/{text}")
    suspend fun generateImage(@Path("text") text:String): Response<String>
}