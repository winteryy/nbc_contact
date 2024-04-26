package com.nbcteam5.nbccontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentMainContactListBinding

class MainContactListFragment: Fragment() {

    private var _binding: FragmentMainContactListBinding? = null
    private val binding get() = _binding!!
    private val adapter: MainContactListAdapter by lazy {
        MainContactListAdapter {
            (requireActivity() as MainActivity).detailFromList(it)
        }
    }

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

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        refreshList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun refreshList() {
        adapter.submitList(ContactDatabase.nameSorting())
    }
}