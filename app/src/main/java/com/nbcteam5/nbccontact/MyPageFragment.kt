import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import coil.load
import com.nbcteam5.nbccontact.R
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.databinding.FragmentMyPageBinding
import kotlin.random.Random

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

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
        randomPeople()
        binding.contact.setOnClickListener(){
            moveDetail()
        }
    }
    private fun moveDetail(){
        val transaction = parentFragmentManager
        transaction.replace(R.id.fragment_container, fragment_contact_detail)
        transaction.commit()
    }
    }


    private fun
            randomPeople(){
        val userList = ContactDatabase.getContactData()
        val randomUser = userList[Random.nextInt(userList.size)]
        binding.ivRvUser.load(randomUser.profileImage)
        binding.ivRvFavorite.isVisible = randomUser.isFavorite
        binding.tvRvUserName.text = randomUser.name

    }
    private fun initViews(){
        val user = ContactDatabase.getUserData()
        binding.name.text = user.name
        binding.number.text = user.phoneNumber
        binding.home.text = user.address
        binding.email.text = user.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

