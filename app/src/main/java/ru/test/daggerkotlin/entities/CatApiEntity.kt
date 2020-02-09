package ru.test.daggerkotlin.entities

import com.google.gson.annotations.SerializedName

data class CatApiEntity (
//    val breeds: List<String>,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
//    val width: Int,
//    val height: Int
)