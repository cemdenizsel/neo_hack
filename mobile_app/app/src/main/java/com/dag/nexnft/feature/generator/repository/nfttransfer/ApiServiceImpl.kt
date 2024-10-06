package com.dag.nexnft.feature.generator.repository.nfttransfer

import retrofit2.Retrofit


class ApiServiceImpl(retrofit: Retrofit) : ApiSource {

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

}