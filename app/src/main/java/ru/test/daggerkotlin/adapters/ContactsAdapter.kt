package ru.test.daggerkotlin.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.test.daggerkotlin.R
import ru.test.daggerkotlin.adapters.itemtouchhelper.ItemTouchHelperAdapter
import ru.test.daggerkotlin.adapters.itemtouchhelper.ItemTouchHelperViewHolder
import ru.test.daggerkotlin.entities.Contact
import javax.inject.Inject

class ContactsAdapter @Inject constructor(val context: Context, val picasso: Picasso) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(), ItemTouchHelperAdapter {

    private val dataset = mutableListOf<Contact>()
    private var listener: OnItemClickListener? = null

    fun setItems(items: List<Contact>) = dataset.apply { clear(); addAll(items)
        notifyDataSetChanged()
    Log.wtf("items", "" + items)}

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.card_contact, parent, false)

        return ContactsViewHolder(viewItem)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = dataset[position]
        holder.name.text = contact.person.name.toString()
        holder.phone.text = contact.contactData.phoneNumber
        holder.email.text = contact.contactData.email
        picasso.load(contact.person.photoUrl).into(holder.photo)
        holder.itemView.setOnClickListener { listener?.onItemClick(contact) }
    }

    inner class ContactsViewHolder(
        itemView: View,
        val name: TextView = itemView.findViewById(R.id.contact_name),
        val phone: TextView = itemView.findViewById(R.id.contact_phone),
        val email: TextView = itemView.findViewById(R.id.contact_email),
        val photo: ImageView = itemView.findViewById(R.id.contact_photo)
    ) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
        override fun onItemSelected() {
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemDissmiss(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnItemClickListener {
        fun onItemClick(item: Contact)
    }
}