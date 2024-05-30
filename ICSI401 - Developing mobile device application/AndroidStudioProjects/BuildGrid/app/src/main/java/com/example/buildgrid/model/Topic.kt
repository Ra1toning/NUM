package com.example.buildgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val name: Int,
    val course: Int,
    @DrawableRes val imageRes: Int
)