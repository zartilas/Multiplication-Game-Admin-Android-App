package com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.R
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.database.FirestoreHelper
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ActivityProfileDetailsBinding
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.models.User
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.Constants


class ProfileDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileDetailsBinding
    private lateinit var modelUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        init()
    }

    private fun init() {
        loadProfileDetails()
        setupUI()
    }

    private fun loadProfileDetails() {

        showProgressDialog()

        FirestoreHelper().getUserDetails(this, FirestoreHelper().getCurrentUserID())
    }

    fun successProfileDetailsFromFirestore(user: User) {

        // Hide the progress dialog
        hideProgressDialog()

        modelUser = user

        binding.apply {
            // Populate the user details in the input texts.
            textViewFullName.text = modelUser.fullName
            textViewNameValue.text = modelUser.fullName
            textViewEmailValue.text = modelUser.email
            textViewDateRegisteredValue.text = Constants.DATE_FORMAT.format(modelUser.dateRegistered)
        }

    }

    private fun setupUI() {
        setupActionBar()
        binding.btnLogout.setOnClickListener{
            playButtonPressSound(this)
            FirebaseAuth.getInstance().signOut()
            goToSignInActivity(this)
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            textViewLabel.text = getString(R.string.txt_profile)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true) // Enable back button
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp) // Set custom back button icon
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                playButtonPressSound(this)
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}