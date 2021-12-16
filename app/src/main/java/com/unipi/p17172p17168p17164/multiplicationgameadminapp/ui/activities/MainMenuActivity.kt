package com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities

import android.content.Intent
import android.os.Bundle
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ActivityMainMenuBinding

class MainMenuActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityMainMenuBinding
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnUsers.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, UsersListActivity::class.java)
                startActivity(intent)
            }
            imgBtnProfile.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}