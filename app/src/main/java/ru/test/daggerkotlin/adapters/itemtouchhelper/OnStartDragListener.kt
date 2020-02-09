package ru.test.daggerkotlin.adapters.itemtouchhelper

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}