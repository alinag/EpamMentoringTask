package ru.test.daggerkotlin.converters

import androidx.room.TypeConverter
import ru.test.daggerkotlin.entities.Name
import ru.test.daggerkotlin.entities.Person
import java.util.*


class PersonConverter {
    @TypeConverter
    fun fromString(value: String?): Person? {
        return if (value == null) null else Person(Name( value.split("///")[0], value.split("///")[1]), value.split("///")[2])
    }

    @TypeConverter
    fun personToString(person: Person?): String? {
        return "${person?.name?.name}///${person?.name?.lastName}///${person?.photoUrl}"
    }
}