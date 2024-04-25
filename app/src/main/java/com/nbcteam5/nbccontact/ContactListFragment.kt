package com.nbcteam5.nbccontact

import android.content.Context
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
import java.lang.IllegalArgumentException


class ContactListFragment : Fragment(),
ItemClickListener{

    val contactFavoriteList: MutableList<ContactData> = mutableListOf<ContactData>()


    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val contactListAdapter by lazy { ContactListAdapter(contactFavoriteList,this) }

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            mainActivity = context
        }else{
            throw IllegalArgumentException("Activity는 MainActivity여야함")
        }
    }

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



        return binding.root
    }




    companion object {

        var listGrid = 1            // listGrid에 0을 입력함으로써 Title을 구현하기 위함
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactListAdapter(contactFavoriteList,this)
        adapter.itemClick = this
        binding.recyclerView.adapter = adapter



        onViewCreateInit()

    }



    private fun onViewCreateInit() {
        var sortedList = ContactDatabase.nameSorting()
        val sortedAdapter = ContactListAdapter(sortedList,this)
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

    override fun onItemClick(contact: ContactData) {

        val contactDetailFragment = ContactDetailFragment.newInstance(contact)
        parentFragmentManager.beginTransaction()
            .replace(R.id.contactListFragment, contactDetailFragment)
            .addToBackStack(null)
            .commit()
    }
}


