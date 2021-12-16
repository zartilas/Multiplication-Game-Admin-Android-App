package com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import com.unipi.p17172.emarket.utils.SnackBarErrorClass
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.R
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.database.FirestoreHelper
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ActivityUserDetailsBinding
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.models.User
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.Constants
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.SnackBarSuccessClass
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.Utils


class UserDetailsActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityUserDetailsBinding
    private var userId: String = ""
    private lateinit var modelUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        if (checkIntentExtras()) {
            loadUserDetails()
            setupUI()
        }
        else {
            // todo show error dialog
        }
    }

    private fun checkIntentExtras(): Boolean {
        intent.apply{
            if (intent.hasExtra(Constants.EXTRA_USER_ID)) {
                userId = intent.getStringExtra(Constants.EXTRA_USER_ID).toString()
                return true
            }
            return false
        }
    }

    private fun loadUserDetails() {

        showProgressDialog()

        FirestoreHelper().getUserDetails(this@UserDetailsActivity, userId)
    }

    fun successUserDetailsFromFirestore(user: User) {

        // Hide the progress dialog
        hideProgressDialog()

        modelUser = user

        binding.apply {
            // Populate the user details in the input texts.
            inputTxtFullName.setText(modelUser.fullName)
            switchBtnAdminAccess.isChecked = modelUser.admin
        }

    }

    /**
     * A function to update user profile details to the firestore.
     */
    private fun updateUserProfileDetails() {

        showProgressDialog()

        val userHashMap = HashMap<String, Any>()

        binding.apply {
            val fullName = inputTxtFullName.text.toString()
            val adminAccess = switchBtnAdminAccess.isChecked

            if (fullName != modelUser.fullName)
                userHashMap[Constants.FIELD_FULL_NAME] = fullName

            if (adminAccess != modelUser.admin)
                userHashMap[Constants.FIELD_ADMIN] = adminAccess
        }

        // call the registerUser function of FireStore class to make an entry in the database.
        FirestoreHelper().updateUserProfileData(
            this,
            userId,
            userHashMap
        )
    }

    /**
     * A function to notify the success result and proceed further accordingly after updating the user details.
     */
    fun userProfileUpdateSuccess() {

        // Hide the progress dialog
        hideProgressDialog()

        SnackBarSuccessClass
            .make(binding.root, getString(R.string.txt_success_done), getString(R.string.txt_success_update_user))
            .show()
    }

    private fun validateFields(): Boolean {
        binding.apply {
            return when {
                TextUtils.isEmpty(inputTxtFullName.text.toString().trim { it <= ' ' }) -> {
                    SnackBarErrorClass
                        .make(root, getString(R.string.txt_error_empty_full_name))
                        .show()
                    inputTxtLayoutFullName.requestFocus()
                    inputTxtLayoutFullName.error = getString(R.string.txt_error_empty_full_name)
                    btnSave.startAnimation(AnimationUtils.loadAnimation(
                        this@UserDetailsActivity, R.anim.anim_shake))
                    false
                }
                else -> true
            }
        }
    }

    private fun setupUI() {
        setupActionBar()
        setupClickListeners()

        binding.apply {
            inputTxtFullName.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus)
                    Utils().hideSoftKeyboard(this@UserDetailsActivity, v)
            }
            inputTxtFullName.addTextChangedListener(object: TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    inputTxtLayoutFullName.isErrorEnabled = false
                }
                // Not Needed
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
            })
        }

        // start/exit activity transitions
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSave.setOnClickListener {
                playButtonPressSound(this@UserDetailsActivity)
                if (validateFields()) {
                    updateUserProfileDetails()
                }
            }
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            textViewLabel.text = getString(R.string.txt_edit_user_details)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true) // Enable back button
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp) // Set custom back button icon
        }
    }
}