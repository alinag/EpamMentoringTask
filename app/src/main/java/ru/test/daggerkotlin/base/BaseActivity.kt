package ru.test.daggerkotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    protected abstract val vmClass: Class<VM>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    protected val viewModel: VM by lazy {
        ViewModelProviders.of(this, factory).get(this.vmClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}