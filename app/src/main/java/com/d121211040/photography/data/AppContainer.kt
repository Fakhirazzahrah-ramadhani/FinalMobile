package com.d121211040.photography.data

import com.d121211040.photography.data.repository.PhotographyRepository
import com.d121211040.photography.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val photograhyRepository: PhotographyRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://pixabay.com"

    private val retrofit = Retrofit.Builder()
//        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val photograhyRepository: PhotographyRepository
        get() = PhotographyRepository(retrofitService)
}