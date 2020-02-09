package ru.test.daggerkotlin.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.test.daggerkotlin.db.AppDatabase
import ru.test.daggerkotlin.entities.Contact
import javax.inject.Inject

class DatabaseRepository @Inject constructor(val db: AppDatabase) : Repository {

//    @Inject
//    lateinit var db: AppDatabase
//    val repository = db.usersDao()

    override fun update(contact: Contact) {
        GlobalScope.launch { db.usersDao().update(contact) }
    }

    override suspend fun loadAll(): List<Contact> =
        //            Log.wtf("load", "" + db.usersDao() /*+ " " + db.usersDao().getAll()*/ )
        withContext(Dispatchers.Default) {
            //            Log.wtf("load", "" + db.usersDao() /*+ " " + db.usersDao().getAll()*/ )
            db.usersDao().getAll()
        }

    override suspend fun load(id: Long): Contact =
        withContext(Dispatchers.Default) {db.usersDao().get(id) }

    override fun save(contact: Contact) {
        GlobalScope.launch { db.usersDao().insert(contact)

//        Log.wtf("contact", "" + db.usersDao().getAll())
        }
    }
    override fun saveAll(contacts: List<Contact>) {
        GlobalScope.launch {
            db.usersDao().insertAll(contacts)
        Log.wtf("tt", "" + db.usersDao() /*+ " " + db.usersDao().getAll()*/)
        }
    }

    override fun delete(contact: Contact) {
        GlobalScope.launch { db.usersDao().delete(contact) }
    }

    override fun deleteAll() {
        GlobalScope.launch { db.usersDao().deleteAll() }
    }
}