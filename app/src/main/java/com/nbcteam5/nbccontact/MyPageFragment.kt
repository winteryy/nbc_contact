import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nbcteam5.nbccontact.data.ContactData
import com.nbcteam5.nbccontact.data.ContactDatabase
import com.nbcteam5.nbccontact.data.UserData
import com.nbcteam5.nbccontact.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!
    private val contactData : ContactData? = null

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
    }
    private fun initViews(){
        val user = getUserInfo()
        binding.name.text = user.name
        binding.number.text = user.phoneNumber
        binding.home.text = user.address
        binding.email.text = user.email
    }
    private fun getUserInfo(): UserData {
        val result = (contactData as ContactDatabase).getUserData()
        return UserData(
            name = result.name,
            phoneNumber = result.phoneNumber,
            address = result.address,
            email = result.email
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

