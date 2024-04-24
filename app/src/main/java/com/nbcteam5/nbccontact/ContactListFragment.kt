package com.nbcteam5.nbccontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentContactListBinding


class ContactListFragment : Fragment() {

    val contactFavoriteList: MutableList<ContactData> = mutableListOf<ContactData>()

    //lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val contactListAdapter by lazy { ContactListAdapter(contactFavoriteList) }

    private val mainActivity by lazy { context as MainActivity }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContactListBinding.inflate(inflater,container,false)
        binding.recyclerView.layoutManager = LinearLayoutManager(mainActivity,
            LinearLayoutManager.VERTICAL,false)

        binding.recyclerView.run{
            adapter = contactListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        contactListAdapter.itemClick = object : ContactListAdapter.ItemClick {
            override fun onClick(view: View, contact: Int) {

                //디테일 프레그먼트를 적용한다면
                // startContactDetailFragment(contact)
            }

//            override fun onStarClick(view: View, contact: ContactData) {
//                FavoriteManager.toggleFavoriteContact(contact)
//
//            }

        }

        return binding.root
    }

    //디테일프레그먼트로 데이터 전달


    companion object {
        var userPosition = 0
        var listGrid = 1            // listGrid에 0을 입력함으로써 Title을 구현하기 위함
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //navigateToAddDialog()
        onViewCreateInit()

    }

    //연락처 추가 다이얼로그
//    private fun navigateToAddDialog() {
//        TODO("Not yet implemented")
//    }


    private fun onViewCreateInit() {
        var sortedList = ContactDatabase.nameSorting()
        val sortedAdapter = ContactListAdapter(sortedList)
        with(binding){
            recyclerView.adapter = sortedAdapter
            btnListGrid.setOnClickListener {
                binding.recyclerView.apply {
                    listGrid *= -1
                    when(listGrid){
                        1 -> {
                            layoutManager = LinearLayoutManager(mainActivity,
                                LinearLayoutManager.VERTICAL,false)
                            btnListGrid.setImageResource(R.drawable.icon_grid_black)
                            notifyDataSetChanged()
                        }
                        -1 -> {
                            layoutManager = GridLayoutManager(mainActivity,3,
                                GridLayoutManager.VERTICAL,false)
                            btnListGrid.setImageResource(R.drawable.icon_list_black)
                            notifyDataSetChanged()
                        }
                    }
                }
            }



        }
    }


    override fun onResume() {
        super.onResume()
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun refreshRecyclerViews(){
        contactListAdapter?.notifyDataSetChanged()

    }
}


