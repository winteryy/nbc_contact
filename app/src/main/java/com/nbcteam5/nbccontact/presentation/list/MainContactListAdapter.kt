package com.nbcteam5.nbccontact.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.LayoutRvUserBinding
import com.nbcteam5.nbccontact.databinding.LayoutRvUserGridBinding
import com.nbcteam5.nbccontact.presentation.detail.BaseViewHolder
import com.nbcteam5.nbccontact.presentation.detail.ContactGridViewHolder
import com.nbcteam5.nbccontact.presentation.detail.ContactLinearViewHolder

class MainContactListAdapter(
    private val layoutType: LayoutType,
    private val onClick: (ContactData) -> Unit,
): ListAdapter<ContactData, BaseViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(layoutType) {
            LayoutType.LINEAR_LAYOUT -> {
                ContactLinearViewHolder(
                    LayoutRvUserBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )
            }
            LayoutType.GRID_LAYOUT -> {
                ContactGridViewHolder(
                    LayoutRvUserGridBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(getItem(position))
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

    enum class LayoutType {
        LINEAR_LAYOUT, GRID_LAYOUT
    }
}