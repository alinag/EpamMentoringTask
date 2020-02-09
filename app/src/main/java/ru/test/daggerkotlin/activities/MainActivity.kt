package ru.test.daggerkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.test.daggerkotlin.R
import ru.test.daggerkotlin.adapters.ContactsAdapter
import ru.test.daggerkotlin.adapters.itemtouchhelper.OnStartDragListener
import ru.test.daggerkotlin.base.BaseActivity
import ru.test.daggerkotlin.entities.*
import ru.test.daggerkotlin.repository.Repository
import ru.test.daggerkotlin.rest.PhotoApi
import ru.test.daggerkotlin.rest.TextApi
import ru.test.daggerkotlin.viewmodels.MainViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.android.synthetic.main.content_main.contact_list as contactsList

class MainActivity : BaseActivity<MainViewModel>(), OnStartDragListener,
    ContactsAdapter.OnItemClickListener {

    override val vmClass = MainViewModel::class.java
    @Inject
    lateinit var contactsAdapter: ContactsAdapter
    @Inject
    lateinit var photoApi: PhotoApi
    @Inject
    lateinit var textApi: TextApi
    val photos = mutableListOf<CatApiEntity>()
    val users = mutableListOf<UserApiEntity>()

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadPhotos()
        downloadUsers()
        contactsAdapter.setListener(this)
        contactsList.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        itemTouchHelper.attachToRecyclerView(contactsList)
    }

    private fun downloadPhotos() {
        val photoCall: Call<List<CatApiEntity>> = photoApi.getPhotos(10)
        photoCall.enqueue(object : Callback<List<CatApiEntity>> {

            override fun onFailure(call: Call<List<CatApiEntity>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<CatApiEntity>>,
                response: Response<List<CatApiEntity>>
            ) {
                if (response.isSuccessful) {

                    Log.wtf("photos", "" + response.body())
                    photos.addAll(response.body()!!) //TODO!!!!
                    randomContacts(photos, users)
                }
            }

        })
    }

    private fun downloadUsers() {
        val usersCall: Call<UserApiList> = textApi.getUsers(10)
        usersCall.enqueue(object : Callback<UserApiList> {

            override fun onFailure(call: Call<UserApiList>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<UserApiList>,
                response: Response<UserApiList>
            ) {
                if (response.isSuccessful) {
                    users.addAll(response.body()!!.users) //TODO!!!!
//                    contactsAdapter.setItems(randomContacts(photos, users))
                    randomContacts(photos, users)
                }
            }


        })
    }

    fun randomContacts(photos: List<CatApiEntity>, users: List<UserApiEntity>) {
        if (photos.size == users.size && photos.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {

                    for (i in photos.indices) {
                        repository.save(
                            Contact(
                                0,
                                Person(photoUrl = photos[i].url, name = users[i].name),
                                ContactData(users[i].phone, users[i].email)
                            )
                        )
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        contacts.addAll(repository.loadAll())
                        CoroutineScope(Dispatchers.Main).launch {
                            contactsAdapter.setItems(contacts)
                        }
                    }
                }

        }
    }

    companion object {
        val contacts = mutableListOf<Contact>()

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemClick(item: Contact) {
        startActivity(
            Intent(this, DetailsActivity::class.java)
                .putExtra(DetailsActivity.EXTRA_CONTACT_ID, item.id)
        )
    }
}
