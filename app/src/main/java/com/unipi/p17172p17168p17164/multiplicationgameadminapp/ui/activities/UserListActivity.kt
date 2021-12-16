package com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.R
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.adapters.UsersListAdapter
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.database.FirestoreHelper
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ActivityUsersListBinding
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.models.User

class UsersListActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityUsersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        loadUsers()
    }

    private fun loadUsers() {

        showProgressDialog()

        FirestoreHelper().getUsersList(this@UsersListActivity)
    }

    /**
     * A function to get the successful tables list from cloud firestore.
     *
     * @param usersList Will receive the tables list from cloud firestore.
     */
    fun successUsersListFromFirestore(usersList: ArrayList<User>) {

        // Hide the progress dialogc
        hideProgressDialog()

        if (usersList.size > 0) {

            val usersListAdapter = UsersListAdapter(this@UsersListActivity, usersList)

            binding.apply {
                recyclerView.run {
                    layoutManager = LinearLayoutManager(this@UsersListActivity)
                    setHasFixedSize(true)
                    adapter = usersListAdapter
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
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp)
        }
    }

    /*override fun onResume() {
        super.onResume()

        loadUsers()
    }*/
}