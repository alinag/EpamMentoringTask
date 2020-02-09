package ru.test.daggerkotlin.converters

import androidx.room.TypeConverter
import ru.test.daggerkotlin.entities.ContactData
import ru.test.daggerkotlin.entities.Name
import ru.test.daggerkotlin.entities.Person
import java.util.*


class ContactDataConverter {
    @TypeConverter
    fun fromString(value: String?): ContactData? {
        return if (value == null) null else ContactData(value.split("///")[0], value.split("///")[1])
    }

    @TypeConverter
    fun contactDataToString(data: ContactData?): String? {
        return "${data?.phoneNumber}///${data?.email}}"
    }
}