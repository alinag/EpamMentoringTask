package ru.test.daggerkotlin.dagger.base

import com.squareup.picasso.Picasso
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.test.daggerkotlin.base.MainApp
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
//        AndroidBindingModule::class,
        AppModule::class]
)
interface AppComponent  : AndroidInjector<MainApp> {

//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<MainApp>()

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MainApp): Builder

        fun build(): AppComponent
    }
    //    fun getContact(): ContactsApi
    fun getPicasso(): Picasso
//    fun getPhotos(): PhotoApi
//    fun getText(): TextApi
//    fun getRetrofit(): Retrofit
//    fun getAdapter(): ContactsAdapter
//    fun inject(app: MainApp)
}