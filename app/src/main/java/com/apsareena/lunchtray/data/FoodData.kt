package com.apsareena.lunchtray.data

import com.apsareena.lunchtray.R
import com.apsareena.lunchtray.model.FoodItem

object FoodData {

    val mainDishes: List<FoodItem> =
        listOf(
            FoodItem(
                R.string.cauliflower,
                R.string.cauliflower_desc,
                R.string.cauliflower_price, false),
            FoodItem(
                R.string.three_bean_chilli,
                R.string.bean_chilli_desc,
                R.string.bean_chilli_price, false
            ),
            FoodItem(
                R.string.mushroom_pasta,
                R.string.mushroom_desc,
                R.string.mushroom_price, false),
            FoodItem(
                R.string.spicy_black_bean_skillet,
                R.string.spicy_bean_desc,
                R.string.spicy_bean_price, false
            )
        )

    val sideDishes: List<FoodItem> =
        listOf(
            FoodItem(
                R.string.summer_salad,
                R.string.summer_salad_desc,
                R.string.summer_salad_price, false
            ),
            FoodItem(
                R.string.butternut_soup,
                R.string.butternut_desc,
                R.string.butternut_soup_price, false
            ),
            FoodItem(
                R.string.spicy_potatoes,
                R.string.spicy_potatoes_desc,
                R.string.spicy_potato_price, false
            ),
            FoodItem(R.string.coconut_rice,
                R.string.coconut_rice_desc,
                R.string.coconut_rice_price, false)
        )

    val accompaniment: List<FoodItem> =
        listOf(
            FoodItem(
                R.string.lunch_roll,
                R.string.lunch_roll_desc,
                R.string.lunch_roll_price, false),
            FoodItem(
                R.string.mixed_berries,
                R.string.mixed_berries_desc,
                R.string.mixed_berries_price, false
            ),
            FoodItem(
                R.string.pickled_veggies,
                R.string.pickled_veggies_desc,
                R.string.pickled_veggies_price, false
            )
        )
}