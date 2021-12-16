package com.unipi.p17172p17168p17164.multiplicationgameadminapp.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * A data model class with required fields.
 */
@Keep
@Parcelize
@IgnoreExtraProperties
data class User(
    var userId: String = "",
    val admin: Boolean = false,
    val fullName: String = "",
    val email: String = "",
    @ServerTimestamp
    val dateRegistered: Date = Date(),
) : Parcelable