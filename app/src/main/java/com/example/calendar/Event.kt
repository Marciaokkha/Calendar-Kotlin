package com.example.calendar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val event: String
) : Parcelable
