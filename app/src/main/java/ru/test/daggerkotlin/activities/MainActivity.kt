package ru.test.daggerkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.test.daggerkotlin.R
import ru.test.daggerkotlin.adapters.ContactsAdapter
import ru.test.daggerkotlin.adapters.itemtouchhelper.OnStartDragListener
import ru.test.daggerkotlin.base.BaseActivity
import ru.test.daggerkotlin.entities.Contact
import ru.test.daggerkotlin.mvp.interfaces.IMainPresenter
import ru.test.daggerkotlin.mvp.interfaces.IMainView
import ru.test.daggerkotlin.repository.Repository
import ru.test.daggerkotlin.viewmodels.MainViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.content_main.contact_list as contactsList
import kotlinx.android.synthetic.main.content_main.loading_spinner as loadingSpinner

class MainActivity : BaseActivity<MainViewModel>(),
    OnStartDragListener,
    ContactsAdapter.OnItemClickListener,
    IMainView {

    override val vmClass = MainViewModel::class.java
    @Inject
    lateinit var contactsAdapter: ContactsAdapter
    private val contacts = mutableListOf<Contact>()

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    @Inject
    lateinit var presenter: IMainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contactsAdapter.setListener(this)
        contactsList.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        itemTouchHelper.attachToRecyclerView(contactsList)
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

    override fun setContactsData(contacts: List<Contact>): Unit = this.contacts.run {
        clear()
        addAll(contacts)
    }


    override fun updateContactsList(contacts: List<Contact>) = contactsAdapter.run {
        setItems(contacts)
        notifyDataSetChanged()
    }


    override fun showError() = Toast.makeText(this, "error!", Toast.LENGTH_LONG).show()


    override fun onSwipeToRefresh() {
        presenter.loadContacts(getAmount())
    }

    override fun getAmount(): Int = 10

    override fun hideLoadingIndicator() {
        loadingSpinner.visibility = View.INVISIBLE
    }
}
