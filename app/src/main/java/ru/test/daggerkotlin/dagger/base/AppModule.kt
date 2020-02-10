package ru.test.daggerkotlin.dagger.base

//import ru.test.daggerkotlin.dagger.annotations.AppContext
//import ru.test.daggerkotlin.dagger.module.MainActivityModule
import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.daggerkotlin.activities.DetailsActivity
import ru.test.daggerkotlin.activities.MainActivity
import ru.test.daggerkotlin.adapters.ContactsAdapter
import ru.test.daggerkotlin.adapters.itemtouchhelper.ItemTouchHelperCallback
import ru.test.daggerkotlin.base.MainApp
import ru.test.daggerkotlin.dagger.annotations.ImageServer
import ru.test.daggerkotlin.dagger.annotations.TextServer
import ru.test.daggerkotlin.dagger.module.DetailsActivityModule
import ru.test.daggerkotlin.dagger.module.MainActivityModule
import ru.test.daggerkotlin.dagger.scopes.ActivityScope
import ru.test.daggerkotlin.dagger.scopes.DetailsActivityScope
import ru.test.daggerkotlin.db.AppDatabase
import ru.test.daggerkotlin.repository.DatabaseRepository
import ru.test.daggerkotlin.repository.Repository
import ru.test.daggerkotlin.mvp.presenter.rest.PhotoApi
import ru.test.daggerkotlin.mvp.presenter.rest.TextApi
import java.io.File
import javax.inject.Singleton


@Module(
    includes = [AndroidSupportInjectionModule::class,
        AppModule.Declaration::class,
        ViewModelModule::class]
)
class AppModule {

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideContext(app: MainApp): Context = app.applicationContext

        @Provides
        @Singleton
        @JvmStatic
        fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

        @Provides
        @Singleton
        @JvmStatic
        fun databaseRepo(appDatabase: AppDatabase): Repository  = DatabaseRepository(appDatabase)
    }
//        @Singleton
//        @Provides
//        fun provideApp(): MainApp = MainApp()

    //picasso module
    @Provides
    @Singleton
    fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso =
        Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .build()

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader =
        OkHttp3Downloader(okHttpClient)


    @Provides
    fun okHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .build()

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
//                    Timber.d(message)
            }

        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, /*Constants.MAX_CACHE_SIZE*/ 10 * 1000 * 1000L)
    }

    @Provides
    fun file(/*@AppContext */context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    //rest module
//
//    @Provides
//    @PeopleServer
//    fun contactsApi(@PeopleServer retrofit: Retrofit): ContactsApi =
//        retrofit.create(ContactsApi::class.java)

    @Provides
    @Singleton
    fun photoApi(@ImageServer retrofit: Retrofit): PhotoApi = retrofit.create(PhotoApi::class.java)

    @Provides
    @Singleton
    fun textApi(@TextServer retrofit: Retrofit): TextApi = retrofit.create(TextApi::class.java)


    @TextServer
    @Provides
    @Singleton
    fun retrofitPerson(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(gsonConverterFactory)
        .build()


    //https://api.thecatapi.com/v1/images/search?mime_types=jpg
    //        @ContactsApplicationScope
    @ImageServer
    @Provides
    @Singleton
    fun retrofitPhotos(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory //x
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Provides
//    fun gsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


//    }

    @Provides
    fun itemTouchHelper(callback: ItemTouchHelper.Callback): ItemTouchHelper = ItemTouchHelper(callback)

    @Provides
    fun callback(adapter: ContactsAdapter): ItemTouchHelper.Callback = ItemTouchHelperCallback(adapter)

    @Provides
    @Singleton
    fun contactsAdapter(context: Context, picasso: Picasso): ContactsAdapter = ContactsAdapter(context, picasso)


//    @Provides
//    @Singleton
//    fun repository(context: Context): Repository = DatabaseRepository(context)





    @Module
    interface Declaration {

        @ActivityScope
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        fun mainActivityInjector(): MainActivity

        @DetailsActivityScope
        @ContributesAndroidInjector(modules = [DetailsActivityModule::class])
        fun detailsActivityInjector(): DetailsActivity

    }
}
