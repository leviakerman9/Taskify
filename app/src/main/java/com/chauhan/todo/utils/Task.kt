package com.chauhan.todo.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val title:String="",
    val description:String=""
):Parcelable
