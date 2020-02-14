package ru.test.daggerkotlin.mvp.presenter

import io.reactivex.Observable
import ru.test.daggerkotlin.entities.Contact
import ru.test.daggerkotlin.entities.RandomCatPhoto
import ru.test.daggerkotlin.mvp.interfaces.IMainPresenter
import ru.test.daggerkotlin.mvp.presenter.rest.PhotoApi
import ru.test.daggerkotlin.mvp.presenter.rest.TextApi
import javax.inject.Inject

class MainPresenter : IMainPresenter {
    @Inject
    lateinit var photoApi: PhotoApi
    @Inject
    lateinit var textApi: TextApi

    override fun loadContacts(amount: Int): List<Contact> {
        val result = emptyList<Contact>()
        val photoObservable: Observable<List<RandomCatPhoto>> = photoApi.getPhotos()

        return result
    }

}