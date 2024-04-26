package com.nbcteam5.nbccontact

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nbcteam5.nbccontact.data.ContactData

class MainContactListAdapter(
    private val onClick: (ContactData) -> Unit
): ListAdapter<ContactData, MainContactListAdapter.ContactViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(

        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    inner class ContactViewHolder(
//        private val binding: ItemContactBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ContactData) {

        }
    }

    companion object {
        private val DIFF_UTIL = object: DiffUtil.ItemCallback<ContactData>() {
            override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
                return oldItem == newItem
            }

        }
    }
}