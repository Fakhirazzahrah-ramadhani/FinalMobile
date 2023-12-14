package com.d121211040.photography.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Photography(
    val collections: Int?,
    val comments: Int?,
    val downloads: Int?,
    val id: Int?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val imageWidth: Int?,
    val largeImageURL: String?, //foto lebih besar
    val likes: Int?,
    val pageURL: String?, //tmptambilfoto
    val previewHeight: Int?,
    val previewURL: String?,
    val previewWidth: Int?,
    val tags: String?, //nama bunganya
    val type: String?,
    val user: String?, //nama pemilik foto
    val userImageURL: String?, //profile photo pemilik
    val user_id: Int?,
    val views: Int?,
    val webformatHeight: Int?,
    val webformatURL: String?, //foto ukuran normal
    val webformatWidth: Int?
):Parcelable