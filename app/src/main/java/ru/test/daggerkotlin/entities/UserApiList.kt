package ru.test.daggerkotlin.entities

import com.google.gson.annotations.SerializedName


data class UserApiList (
 @SerializedName("results")
 val users: List<UserApiEntity>
)