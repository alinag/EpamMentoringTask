package ru.test.daggerkotlin.mvp.interfaces

import ru.test.daggerkotlin.entities.Contact

interface IModel {
    fun getUsers(amount: Int): List<Contact>
}