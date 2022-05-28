package com.apsareena.lunchtray.model

import android.annotation.SuppressLint
import androidx.annotation.BoolRes
import androidx.annotation.StringRes

class FoodItem(
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @StringRes val price: Int,
    var selected: Boolean
)