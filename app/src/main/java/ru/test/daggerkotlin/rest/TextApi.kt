package ru.test.daggerkotlin.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.daggerkotlin.dagger.annotations.ImageServer
import ru.test.daggerkotlin.dagger.annotations.TextServer
import ru.test.daggerkotlin.entities.UserApiEntity
import ru.test.daggerkotlin.entities.UserApiList

@ImageServer
interface TextApi {

//    @TextServer
    @GET("api/")
    fun getUsers(@Query("results") amount: Int): Call<UserApiList>

}
