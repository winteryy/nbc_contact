package com.nbcteam5.nbccontact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.LayoutRvUserBinding

class MainContactListAdapter(
    private val onClick: (ContactData) -> Unit
): ListAdapter<ContactData, MainContactListAdapter.ContactViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutRvUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    inner class ContactViewHolder(
        private val binding: LayoutRvUserBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ContactData) {
            binding.apply {
                tvRvUserName.text = item.name
                ivRvUser.load(item.profileImage)
                ivRvFavorite.load(if(item.isFavorite) R.drawable.baseline_star_filled_32 else R.drawable.baseline_star_border_32)
                root.setOnClickListener {
                    onClick(item)
                }
            }
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