package ru.test.daggerkotlin.dagger.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import ru.test.daggerkotlin.activities.MainActivity
import ru.test.daggerkotlin.dagger.scopes.ActivityScope


@Module
interface MainActivityModule {

    @ActivityScope
    @Binds
    fun provideActivity(activity: MainActivity): Activity

}
