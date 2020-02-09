package ru.test.daggerkotlin.rest
//
//import io.reactivex.Maybe
//import ru.test.daggerkotlin.entities.CatPhoto
//import javax.inject.Inject
//import javax.inject.Singleton
//
//
//@Singleton
//class RestLayerImpl @Inject constructor(val photoApi: PhotoApi) : RestLayer {
//
//
//    override fun getCatPhoto(): Maybe<CatPhoto> {
//        return photoApi.getPhoto()
//            .map { networkCat -> CatPhoto(networkCat.url, networkCat.height, networkCat.width) }
//    }
//
//}