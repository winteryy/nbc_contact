import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.nbcteam5.nbccontact.R

class MyPageFragment : Fragment() {

    private lateinit var imageView: ShapeableImageView
    private lateinit var nameTextView: MaterialTextView
    private lateinit var numberTextView: MaterialTextView
    private lateinit var homeTextView: MaterialTextView
    private lateinit var emailTextView: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)
        imageView = view.findViewById(R.id.image)
        nameTextView = view.findViewById(R.id.name)
        numberTextView = view.findViewById(R.id.number)
        homeTextView = view.findViewById(R.id.home)
        emailTextView = view.findViewById(R.id.email)

        val user = getUserInfo()


        nameTextView.text = user.name
        numberTextView.text = user.phoneNumber
        homeTextView.text = user.address
        emailTextView.text = user.email

        return view
    }

//    val btn = findViewById<Button>(R.id.btn)
//    btn.setOnClickListener{
//        finish()
//    }
    private fun getUserInfo(): User {
        return User(
            name = "새이름",
            phoneNumber = "010-1234-5678",
            address = "서울시 여름구 2동",
            email = "newname@naver.com"
        )
    }


    // 사용자 정보 모델 클래스
    data class User(
        val name: String,
        val phoneNumber: String,
        val address: String,
        val email: String
    )
}
