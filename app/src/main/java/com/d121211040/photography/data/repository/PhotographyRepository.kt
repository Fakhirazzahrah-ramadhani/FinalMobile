package com.d121211040.photography.data.repository

import com.d121211040.photography.data.response.GetPhotographyResponse
import com.d121211040.photography.data.source.remote.ApiService

class PhotographyRepository (private val  apiService: ApiService) {

   suspend fun getPhotography(): GetPhotographyResponse {
       return apiService.getPhotography()
   }
}