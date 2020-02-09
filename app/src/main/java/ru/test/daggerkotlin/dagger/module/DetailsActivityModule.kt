package ru.test.daggerkotlin.dagger.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import ru.test.daggerkotlin.activities.DetailsActivity
import ru.test.daggerkotlin.activities.MainActivity
import ru.test.daggerkotlin.dagger.scopes.ActivityScope
import ru.test.daggerkotlin.dagger.scopes.DetailsActivityScope


@Module
interface DetailsActivityModule {

    @DetailsActivityScope
    @Binds
    fun provideActivity(activity: DetailsActivity): Activity

}
