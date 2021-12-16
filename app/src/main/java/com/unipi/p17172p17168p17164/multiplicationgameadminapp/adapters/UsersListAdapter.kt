package com.unipi.p17172p17168p17164.multiplicationgameadminapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.R
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.databinding.ItemUserBinding
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.models.User
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities.BaseActivity
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities.UserDetailsActivity
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.ui.activities.UserLogsListActivity
import com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils.Constants


/**
 * A adapter class for tables list items.
 */
open class UsersListAdapter(
    private val activity: Activity,
    private var list: ArrayList<User>
) : RecyclerView.Adapter<UsersListAdapter.UsersViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(activity),
                parent,
                false)
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     */
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val model = list[position]

        holder.binding.apply {
            txtViewHeader.text = model.fullName
            txtViewEmail.text = model.email
            imgViewEdit.setOnClickListener {
                BaseActivity().playButtonPressSound(activity)
                val intent = Intent(activity, UserDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_USER_ID, model.userId)
                activity.startActivity(intent)
            }
        }
        holder.itemView.setOnClickListener {
            BaseActivity().playButtonPressSound(activity)
            val intent = Intent(activity, UserLogsListActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_ID, model.userId)
            activity.startActivity(intent)
        }

        // Slide from right animation
        val animation: Animation =
            AnimationUtils.loadAnimation(activity, R.anim.anim_from_right)
        holder.itemView.startAnimation(animation)
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}