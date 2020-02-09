package ru.test.daggerkotlin.entities

import androidx.room.Entity

@Entity
data class Person @JvmOverloads constructor(
    val name: Name = Name(),
    val photoUrl: String = "https://www.google.ru/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
)