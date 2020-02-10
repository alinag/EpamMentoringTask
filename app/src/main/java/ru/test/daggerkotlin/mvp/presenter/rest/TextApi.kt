package ru.test.daggerkotlin.mvp.presenter.rest

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.daggerkotlin.dagger.annotations.ImageServer
import ru.test.daggerkotlin.entities.UserApiList

@ImageServer
interface TextApi {

    @GET("api/")
    fun getUsers(@Query("results") amount: Int): Observable<UserApiList>

}
