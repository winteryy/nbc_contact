package com.nbcteam5.nbccontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nbcteam5.nbccontact.ContactListFragment.Companion.listGrid
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.databinding.LayoutRvUserBinding
import com.nbcteam5.nbccontact.databinding.LayoutRvUserGridBinding
import java.lang.Exception

class ContactListAdapter(private var userData:List<ContactData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var holderList:Holder
    private lateinit var holdGrid:GridHolder


    companion object{
        private const val LINEAR_LAYOUT = 1
        private const val GRID_LAYOUT = -1
    }


    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick:ItemClick? = null


    override fun getItemViewType(position: Int): Int {
        when(listGrid){
            LINEAR_LAYOUT -> listGrid = LINEAR_LAYOUT
            GRID_LAYOUT -> listGrid = GRID_LAYOUT
        }
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
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it,position)
        }

        when (listGrid) {
            1 -> {
                holderList = holder as Holder
                with(holderList) {

                    userData[position].name?.let { name.text = it }
                    //   userData[position].profileImage?.let { image.setImageResource(it.toInt()) }
//                    userData[position].profilePath?.let { image.setImageURI(it.toUri()) }
                    when (userData[position].isFavorite) {
                        true -> favorite.setImageResource(R.drawable.star_full)
                        false -> favorite.setImageResource(R.drawable.star_empty)
                    }

                    // 짝수번 홀수번일때 아이템 번갈아 나타내기
//                    if (position % 2 == 0) {
//                       // itemView.setBackgroundResource(R.layout.layout_rv_user)
//                        itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.theme_beige))
//                    } else {
//                       // itemView.setBackgroundResource(R.layout.layout_rv_user2)
//                        itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.theme_light_green))
//                    }
                }
            }
            -1 -> {
                holdGrid = holder as GridHolder
                with(holdGrid) {

                    userData[position].name?.let { name.text = it }
                    // userData[position].profileImage?.let { image.setImageResource(it.toInt()) }


                    when (userData[position].isFavorite) {
                        true -> favorite.setImageResource(R.drawable.star_full)
                        false -> favorite.setImageResource(R.drawable.star_empty)
                    }
                }
            }
            else -> throw  Exception("홀더를 캐스팅할 수 없음")
        }
    }

    override fun getItemCount(): Int {
        return userData.size
    }
}

