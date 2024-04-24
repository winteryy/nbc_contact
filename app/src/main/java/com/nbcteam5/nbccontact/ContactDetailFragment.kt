package com.nbcteam5.nbccontact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentContactDetailBinding

class ContactDetailFragment: Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    private var isFavorite = false

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
        val contactData = ContactDatabase.getContactData()[0]
        isFavorite = contactData.isFavorite

        binding.toolBar.setNavigationOnClickListener {
            //TODO 뒤로가기
        }

        binding.apply {
            profileImageView.load(contactData.profileImage)
            nameContent.setContentText(contactData.name)
            phoneNumberContent.setContentText(contactData.phoneNumber)
            addressContent.setContentText(contactData.address)
            emailContent.setContentText(contactData.email)
            toolBar.title = contactData.name
            toolBar.setOnMenuItemClickListener { menu ->
                when(menu.itemId) {
                    R.id.favoriteButton -> {
                        menu.icon =
                            if(isFavorite) ContextCompat.getDrawable(requireContext(), R.drawable.baseline_star_border_32)
                            else ContextCompat.getDrawable(requireContext(), R.drawable.baseline_star_filled_32)
                        isFavorite = !isFavorite
                        //TODO 실제 업데이트
                    }
                    else -> {
                        Unit
                    }
                }
                return@setOnMenuItemClickListener menu != null
            }

            callButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("tel:"+contactData.phoneNumber)
                })
            }
            smsButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:${contactData.phoneNumber}")
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}