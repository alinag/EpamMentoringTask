package ru.test.daggerkotlin.entities

data class ContactData @JvmOverloads constructor(
    val phoneNumber: String = "",
    val email: String = "email"
    )