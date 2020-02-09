package ru.test.daggerkotlin.base

import android.app.Activity
import android.app.Application
import androidx.room.Room
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ru.test.daggerkotlin.dagger.base.DaggerAppComponent
import ru.test.daggerkotlin.db.AppDatabase
import javax.inject.Inject


class MainApp : Application(), HasActivityInjector {
    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

//    @Inject
//    lateinit var database: AppDatabase

//    fun getDatabase() = database

    override fun onCreate() {
        super.onCreate()
        instance = this
//        database = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector() = injector

    companion object {
        lateinit var instance: MainApp
    }
}