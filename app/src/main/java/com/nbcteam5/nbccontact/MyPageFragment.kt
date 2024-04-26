import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nbcteam5.nbccontact.databinding.FragmentMyPageBinding

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

        val user = getUserInfo()

        binding.name.text = user.name
        binding.number.text = user.phoneNumber
        binding.home.text = user.address
        binding.email.text = user.email

//        binding.btn.setOnClickListener { requireActivity().finish() }
    }

    private fun getUserInfo(): User {
        return User(
            name = "아무개",
            phoneNumber = "010-4321-9876",
            address = "경기도 스타시 르타동",
            email = "noonedog@naver.com"
        )
    }

    data class User(
        val name: String,
        val phoneNumber: String,
        val address: String,
        val email: String
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

