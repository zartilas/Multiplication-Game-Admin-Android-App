package com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.R
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.adapters.UserLogListAdapter
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.database.FirestoreHelper
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ActivityUserLogsListBinding
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.models.UserLog
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.Constants

class UserLogsListActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityUserLogsListBinding
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLogsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }


    private fun init() {
        if (intent.hasExtra(Constants.EXTRA_USER_ID))
            userId = intent.getStringExtra(Constants.EXTRA_USER_ID).toString()
        setupUI()
        loadUserLogs()
    }

    private fun loadUserLogs() {

        showProgressDialog()

        FirestoreHelper().getUserLogEntries(this, userId)
    }
    
    /**
     * A function to get the successful tables list from cloud firestore.
     *
     * @param userLogsList Will receive the tables list from cloud firestore.
     */
    fun successUserLogsFromFireStore(userLogsList: ArrayList<UserLog>) {

        hideProgressDialog()

        if (userLogsList.size > 0) {

            val userLogAdapter = UserLogListAdapter(this, userLogsList)

            binding.apply {
                recyclerView.run {
                    layoutManager = LinearLayoutManager(this@UserLogsListActivity, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)

                    adapter = userLogAdapter
                }
                layoutEmptyState.root.visibility = View.GONE
            }
        }
        else
            binding.layoutEmptyState.root.visibility = View.VISIBLE
    }

    fun hideLogs() {
        hideProgressDialog()
        binding.layoutEmptyState.root.visibility = View.VISIBLE
    }

    private fun setupUI() {
        setupActionBar()        
    }
    
    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            textViewLabel.text = getString(R.string.txt_logs)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp)
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