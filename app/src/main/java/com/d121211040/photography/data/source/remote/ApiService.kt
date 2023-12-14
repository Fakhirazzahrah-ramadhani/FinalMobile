package com.d121211040.photography.data.source.remote

import com.d121211040.photography.data.response.GetPhotographyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   // https://pixabay.com/api/?key=41249929-965ed05871767e5081c2a3b07&q=yellow+flowers&image_type=photo&pretty=true
    @GET("api/")
    suspend fun getPhotography(
       @Query("key") key:String = "41249929-965ed05871767e5081c2a3b07",
       @Query("q") q:String = "yellow+flowers",
       @Query("image_type") image_type:String = "all",
       @Query("pretty") pretty:String = "true"
    ): GetPhotographyResponse

}