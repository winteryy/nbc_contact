package com.nbcteam5.nbccontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentMyPageBinding
import kotlin.random.Random

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val recommendUserData = getRandomPeople()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.contact.setOnClickListener {
            moveDetail()
        }
    }

    private fun moveDetail(){
        (requireActivity() as MainActivity).detailFromState(recommendUserData)
    }


    private fun getRandomPeople():ContactData{
        val userList = ContactDatabase.getContactData()
        return userList[Random.nextInt(userList.size)]
    }

    private fun initViews(){
        val user = ContactDatabase.getUserData()
        binding.name.text = user.name
        binding.number.text = user.phoneNumber
        binding.home.text = user.address
        binding.email.text = user.email
        binding.ivRvUser.load(recommendUserData.profileImage)
        binding.ivRvFavorite.isVisible = recommendUserData.isFavorite
        binding.tvRvUserName.text = recommendUserData.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
