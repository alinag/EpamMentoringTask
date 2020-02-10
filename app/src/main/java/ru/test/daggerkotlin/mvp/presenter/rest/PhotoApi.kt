package ru.test.daggerkotlin.mvp.presenter.rest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.daggerkotlin.entities.RandomCatPhoto

interface PhotoApi {

    @GET("v1/images/search?mime_types=jpg")
    fun getPhotos(@Query("limit")amount: Int): Observable<List<RandomCatPhoto>>

}
