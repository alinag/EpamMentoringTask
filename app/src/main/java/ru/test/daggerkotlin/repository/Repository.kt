package ru.test.daggerkotlin.repository

import ru.test.daggerkotlin.entities.Contact

interface Repository {
    fun save(contact: Contact)
    fun saveAll(contacts: List<Contact>)
    suspend fun loadAll(): List<Contact>
    suspend fun load(id: Long): Contact
    fun update(contact: Contact)
    fun delete(contact: Contact)
    fun deleteAll()
}