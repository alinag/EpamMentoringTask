package ru.test.daggerkotlin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.test.daggerkotlin.converters.ContactDataConverter
import ru.test.daggerkotlin.converters.PersonConverter
import java.io.Serializable

@Entity(tableName = "contacts")
@TypeConverters(PersonConverter::class, ContactDataConverter::class)
data class Contact @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val person: Person = Person(),
    val contactData: ContactData = ContactData(),
    val description: String = "descr"
)