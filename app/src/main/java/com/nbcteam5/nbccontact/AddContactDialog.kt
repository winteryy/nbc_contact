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
import com.nbcteam5.nbccontact.util.addCallDialog

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
}