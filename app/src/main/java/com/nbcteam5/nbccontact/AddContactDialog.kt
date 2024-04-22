package com.nbcteam5.nbccontact

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nbcteam5.nbccontact.databinding.ActivityAddContactDialogBinding
import com.nbcteam5.nbccontact.databinding.DialogAddBinding

class AddContactDialog : AppCompatActivity() {
    lateinit var binding: ActivityAddContactDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactDialogBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addDialogFloatingActionButton.setOnClickListener {
            addCallDialog()
        }
    }
    private fun addCallDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogAddBinding.inflate(layoutInflater)
            setView(dialogBinding.root)
            setTitle("전화번호부 저장")
            setPositiveButton("확인") { _, _ ->
                with(getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()) {
                    putString("name", dialogBinding.name.text.toString())
                    putString("phoneNumber", dialogBinding.phoneNumber.text.toString())
                    putString("email", dialogBinding.email.text.toString())
                    putString("event", dialogBinding.event.text.toString())
                    apply()
                }
                Toast.makeText(this@AddContactDialog, "전화번호부가 저장되었습니다", Toast.LENGTH_LONG).show()
            }
            setNegativeButton("취소", null)
        }.show()
    }
}