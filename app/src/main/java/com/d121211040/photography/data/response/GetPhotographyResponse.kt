package com.d121211040.photography.data.response

import com.d121211040.photography.data.models.Photography
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPhotographyResponse(
    @SerialName("photography")
    val photography: List<Photography>?,
    @SerialName("total")
    val total: Int?,
    @SerialName("totalHits")
    val totalHits: Int?
)