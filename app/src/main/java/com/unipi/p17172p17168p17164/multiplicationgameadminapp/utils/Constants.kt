package com.unipi.p17172p17168p17164.multiplicationgameadminapp.utils

import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import java.text.SimpleDateFormat
import java.util.*

// Create a custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
/**
 * A custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
 */
object Constants {

    // General Constants
    const val SHARED_PREFERENCES_PREFIX: String = "MultiplicationGameAdminPrefs"
    const val LOGGED_IN_EMAIL: String = "logged_in_email"
    const val SPLASH_SCREEN_DELAY: Long = 1500
    const val VOLUME_MEDIUM: Float = 75f
    val DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
    val SNACKBAR_BEHAVIOR = BaseTransientBottomBar.Behavior().apply {
        setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY) }

    // Firebase Constants
    // This is used for the collection name for USERS.
    const val COLLECTION_USERS: String = "users"
    const val COLLECTION_USER_LOGS: String = "user_logs"

    // Fields
    const val FIELD_DATE_ADDED: String = "dateAdded"
    const val FIELD_FULL_NAME: String = "fullName"
    const val FIELD_ADMIN: String = "admin"
    const val FIELD_USER_ID: String = "userId"
    const val TYPE_SKIP: String = "Skip"
    const val TYPE_TIME_UP: String = "Time Up"
    const val TYPE_MISTAKE: String = "Mistake"
    const val TYPE_SOLVED: String = "Solved"

    // Intent Extras
    const val EXTRA_USER_ID: String = "extraUserId"
}
// END