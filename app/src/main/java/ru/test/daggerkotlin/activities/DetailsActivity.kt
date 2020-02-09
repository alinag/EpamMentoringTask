package ru.test.daggerkotlin.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.test.daggerkotlin.R
import ru.test.daggerkotlin.base.BaseActivity
import ru.test.daggerkotlin.entities.Contact
import ru.test.daggerkotlin.repository.DatabaseRepository
import ru.test.daggerkotlin.repository.Repository
import ru.test.daggerkotlin.viewmodels.MainViewModel
import javax.inject.Inject

class DetailsActivity : BaseActivity<MainViewModel>() {
    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var picasso: Picasso

    var user: Contact? = null

    override val vmClass = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        CoroutineScope(Dispatchers.IO).launch {
            user = repository.load(intent.getLongExtra(EXTRA_CONTACT_ID, 0))
            CoroutineScope(Dispatchers.Main).launch {
                picasso.load(user?.person?.photoUrl).into(big_photo)
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_ID = "contactId"
    }
}
