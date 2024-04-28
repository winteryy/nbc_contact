package com.nbcteam5.nbccontact.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbcteam5.nbccontact.presentation.main.MainActivity
import com.nbcteam5.nbccontact.R
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentMainContactListBinding

class MainContactListFragment: Fragment() {

    private var _binding: FragmentMainContactListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MainContactListAdapter

    private var layoutType = MainContactListAdapter.LayoutType.LINEAR_LAYOUT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainContactListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainContactListAdapter(layoutType) {
            (requireActivity() as MainActivity).detailFromState(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        refreshList()
        binding.btnListGrid.setOnClickListener {
            changeLayoutType()
        }

    }

    private fun changeLayoutType() {
        when(layoutType) {
            MainContactListAdapter.LayoutType.LINEAR_LAYOUT -> {
                layoutType = MainContactListAdapter.LayoutType.GRID_LAYOUT
                adapter = MainContactListAdapter(layoutType) {
                    (requireActivity() as MainActivity).detailFromState(it)
                }
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
                binding.btnListGrid.setImageResource(R.drawable.icon_list_black)
                refreshList()
            }
            MainContactListAdapter.LayoutType.GRID_LAYOUT -> {
                layoutType = MainContactListAdapter.LayoutType.LINEAR_LAYOUT
                adapter = MainContactListAdapter(layoutType) {
                    (requireActivity() as MainActivity).detailFromState(it)
                }
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.btnListGrid.setImageResource(R.drawable.icon_grid_black)
                refreshList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun refreshList() {
        adapter.submitList(ContactDatabase.getSortedContactData())
    }
}