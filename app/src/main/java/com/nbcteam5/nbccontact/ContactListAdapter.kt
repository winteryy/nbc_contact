package com.nbcteam5.nbccontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.nbcteam5.nbccontact.ContactListFragment.Companion.listGrid
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.LayoutRvUserBinding
import com.nbcteam5.nbccontact.databinding.LayoutRvUserGridBinding
import java.lang.Exception

interface ItemClickListener{
    fun onItemClick(contact: ContactData)
}

class ContactListAdapter(
    private val userData: List<ContactData>,
    private val itemClickListener: ContactListFragment
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClick: ItemClickListener? = null

    private var newUserData = userData as MutableList<ContactData>

    private lateinit var holderList:Holder
    private lateinit var holdGrid:GridHolder


    companion object{
        private const val LINEAR_LAYOUT = 1
        private const val GRID_LAYOUT = -1
    }




    override fun getItemViewType(position: Int): Int {
       return listGrid
    }

    inner class Holder(binding: LayoutRvUserBinding): RecyclerView.ViewHolder(binding.root){
        val image = binding.ivRvUser
        val name = binding.tvRvUserName
        val favorite = binding.ivRvFavorite
    }

    inner class GridHolder(binding: LayoutRvUserGridBinding): RecyclerView.ViewHolder(binding.root){
        val image = binding.ivRvUserGrid
        val name = binding.tvUserNameGrid
        val favorite = binding.ivRvFavoriteGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            LINEAR_LAYOUT -> Holder(LayoutRvUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            GRID_LAYOUT -> GridHolder(LayoutRvUserGridBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> throw Exception("layout adapter 연결에 실패")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = userData[position]

        when (listGrid) {
            1 -> {
                holderList = holder as Holder

                with(holderList) {
                    name.text = data.name
                    data.profileImage?.let { image.setImageURI(it.toUri()) }
                    holder.itemView.setOnClickListener {
                        val contact = userData[position]
                        itemClickListener.onItemClick(contact)
                    }
                    if(data.isFavorite){
                        favorite.setImageResource(R.drawable.star_full)
                    }else{
                        favorite.setImageResource(R.drawable.star_empty)
                    }



                    holder.favorite.setOnClickListener {
                        // 별 누를때
                        val newData = data.copy(isFavorite  = !userData[position].isFavorite)

                        if (newData.isFavorite) {
                            holder.favorite.setImageResource(R.drawable.star_full)
                        } else {
                            holder.favorite.setImageResource(R.drawable.star_empty)
                        }

                        ContactBookmarkManager.toggleFavoriteContact(newData)
                        // 원본 데이터 리스트에서 newData로 업데이트
                        newUserData[position] = newData
                    }

                }
            }
            -1 -> {
                holdGrid = holder as GridHolder

                with(holdGrid) {
                    name.text = data.name
                    data.profileImage?.let { image.setImageURI(it.toUri()) }
                    holder.itemView.setOnClickListener {
                        val contact = userData[position]
                        itemClickListener.onItemClick(contact)
                    }
                    holder.favorite.setOnClickListener {
                        // 별 누를때
                        val newData = data.copy(isFavorite  = !userData[position].isFavorite)

                        if (newData.isFavorite) {
                            holder.favorite.setImageResource(R.drawable.star_full)
                        } else {
                            holder.favorite.setImageResource(R.drawable.star_empty)
                        }

                        ContactBookmarkManager.toggleFavoriteContact(newData)
                        // 원본 데이터 리스트에서 newData로 업데이트
                        newUserData[position] = newData
                    }

                }
            }
            else -> throw Exception("홀더를 캐스팅할 수 없음")
        }
    }

    override fun getItemCount(): Int {
        return userData.size
    }
}

