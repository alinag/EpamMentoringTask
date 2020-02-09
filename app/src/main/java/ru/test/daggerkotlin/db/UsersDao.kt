package ru.test.daggerkotlin.db

import androidx.room.*
import ru.test.daggerkotlin.entities.Contact

@Dao
interface UsersDao {
    @Query("select * from contacts")
    suspend fun getAll(): List<Contact>

    @Query("select * from contacts where id=:id limit 1")
     fun get(id: Long): Contact

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(contacts: List<Contact>)

    @Update
    fun update(contacts: Contact)

    @Delete
    fun delete(contacts: Contact)

    @Query("delete from contacts")
    fun deleteAll()
}