package ru.test.daggerkotlin.dagger.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.test.daggerkotlin.base.BaseViewModelFactory
import ru.test.daggerkotlin.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.test.daggerkotlin.dagger.annotations.ViewModelKey
import javax.inject.Singleton


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindListViewModel(vm: MainViewModel): ViewModel

    @Binds
    @Singleton
    fun bindFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}