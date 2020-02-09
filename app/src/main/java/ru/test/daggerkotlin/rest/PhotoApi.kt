package ru.test.daggerkotlin.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.daggerkotlin.dagger.annotations.ImageServer
import ru.test.daggerkotlin.entities.CatApiEntity

//@ImageServer
interface PhotoApi {

//    @GET("v1/images/search?limit=5&mime_types=jpg")
//    @ImageServer
    @GET("v1/images/search?mime_types=jpg")
    fun getPhotos(@Query("limit")amount: Int): Call<List<CatApiEntity>>

}
