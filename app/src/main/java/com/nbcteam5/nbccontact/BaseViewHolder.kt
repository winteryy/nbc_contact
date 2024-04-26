package com.nbcteam5.nbccontact

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.LayoutRvUserBinding
import com.nbcteam5.nbccontact.databinding.LayoutRvUserGridBinding

abstract class BaseViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

        abstract fun onBind(item: ContactData)
}

class ContactLinearViewHolder(
    private val binding: LayoutRvUserBinding,
    private val onClick: (ContactData) -> Unit
): BaseViewHolder(binding) {
    override fun onBind(item: ContactData) {
        binding.apply {
            tvRvUserName.text = item.name
            ivRvUser.load(item.profileImage)
            ivRvFavorite.load(if (item.isFavorite) R.drawable.baseline_star_filled_32 else R.drawable.baseline_star_border_32)
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
}

class ContactGridViewHolder(
    private val binding: LayoutRvUserGridBinding,
    private val onClick: (ContactData) -> Unit
): BaseViewHolder(binding) {
    override fun onBind(item: ContactData) {
        binding.apply {
            tvUserNameGrid.text = item.name
            ivRvUserGrid.load(item.profileImage)
            ivRvFavoriteGrid.load(if (item.isFavorite) R.drawable.baseline_star_filled_32 else R.drawable.baseline_star_border_32)
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
}