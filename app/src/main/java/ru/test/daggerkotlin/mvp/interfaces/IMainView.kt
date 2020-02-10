package ru.test.daggerkotlin.mvp.interfaces

import ru.test.daggerkotlin.entities.Contact

interface IMainView {
    fun setContactsData(contacts: List<Contact>)
    fun updateContactsList(contacts: List<Contact>)
    fun showError()
    fun onSwipeToRefresh()
    fun getAmount(): Int
    fun hideLoadingIndicator()
}