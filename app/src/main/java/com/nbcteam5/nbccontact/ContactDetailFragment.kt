package com.nbcteam5.nbccontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nbcteam5.nbccontact.databinding.FragmentContactDetailBinding

class ContactDetailFragment: Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val contactData = arguments?.getParcelable() 데이터 불러오기

        binding.toolBar.setNavigationOnClickListener {
            //TODO 뒤로가기
        }

//        binding.apply {
//            데이터 바인드 할 부분
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}