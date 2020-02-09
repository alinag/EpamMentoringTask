package ru.test.daggerkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.test.daggerkotlin.entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun usersDao(): UsersDao
}