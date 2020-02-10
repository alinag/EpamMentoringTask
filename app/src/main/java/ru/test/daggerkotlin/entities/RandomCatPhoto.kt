package ru.test.daggerkotlin.entities

import com.google.gson.annotations.SerializedName

data class RandomCatPhoto (
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)