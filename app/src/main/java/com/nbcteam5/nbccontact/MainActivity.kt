package com.nbcteam5.nbccontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nbcteam5.nbccontact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.container, ContactDetailFragment()).commit()
    }
}