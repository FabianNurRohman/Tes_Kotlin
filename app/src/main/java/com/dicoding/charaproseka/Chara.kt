package com.dicoding.charaproseka

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chara(
    val name: String,
    val description: String,
    val photo: String
) : Parcelable
