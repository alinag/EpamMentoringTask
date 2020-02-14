package ru.test.daggerkotlin.mvp.interfaces

import ru.test.daggerkotlin.entities.Contact

interface IMainPresenter {

    fun loadContacts(amount: Int): List<Contact>
}