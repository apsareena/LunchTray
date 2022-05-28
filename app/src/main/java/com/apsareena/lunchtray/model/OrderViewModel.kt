package com.apsareena.lunchtray.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.apsareena.lunchtray.data.FoodData
import java.text.NumberFormat


private const val MAIN_DISH = 1

private const val SIDE_DISH = 2

class OrderViewModel : ViewModel() {

    private val mainDishes = FoodData.mainDishes
    private val sideDishes = FoodData.sideDishes
    private val accompanimentDishes = FoodData.accompaniment


    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<String> = Transformations.map(_totalPrice){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _mainDish = MutableLiveData<String>()
    val mainDish : LiveData<String> = _mainDish

    private val _mainDishPrice = MutableLiveData<String>()
    val mainDishPrice: LiveData<String> = _mainDishPrice

    private val _sideDishPrice = MutableLiveData<String>()
    val sideDishPrice: LiveData<String> = _sideDishPrice

    private val _accompanimentPrice = MutableLiveData<String>()
    val accompanimentPrice: LiveData<String> = _accompanimentPrice

    private val _sideDish = MutableLiveData<String>()
    val sideDish: LiveData<String> = _sideDish

    private val _accompaniment = MutableLiveData<String>()
    val accompaniment: LiveData<String> = _accompaniment

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }

    private fun updatePrice(){
        _price.value =  (_mainDishPrice.value?.toDouble() ?: 0.0) + (_sideDishPrice.value?.toDouble() ?: 0.0) + (_accompanimentPrice.value?.toDouble() ?: 0.0)
        _totalPrice.value = (_price.value?:0.0).plus(0.52)
    }

    fun preferedItem(position: Int, dishId: Int, context: Context){
        val temp = context.resources
        when (dishId){
            MAIN_DISH -> {
                setMainDish(temp.getString(mainDishes[position].title), temp.getString(mainDishes[position].price))
            }
            SIDE_DISH -> {
                setSideDish(temp.getString(sideDishes[position].title), temp.getString(sideDishes[position].price))
            }
            else -> {
                setAccompaniment(temp.getString(accompanimentDishes[position].title), temp.getString(accompanimentDishes[position].price))
            }
        }
    }


    private fun setMainDish(desiredMainDish: String, desiredMainDishPrice: String) {
        _mainDishPrice.value = desiredMainDishPrice
        _mainDish.value = desiredMainDish
        updatePrice()
    }

    private fun setSideDish(desiredSideDish: String, desiredSideDishPrice: String){
        _sideDishPrice.value = desiredSideDishPrice
        _sideDish.value = desiredSideDish
        updatePrice()
    }

    private fun setAccompaniment(accompaniment: String, desiredAccompanimentPrice: String){
        _accompanimentPrice.value = desiredAccompanimentPrice
        _accompaniment.value = accompaniment
        updatePrice()
    }

    fun hasNoMainDishSet(): Boolean {
        return _mainDish.value.isNullOrEmpty()
    }

    fun resetOrder() {
        _mainDish.value = ""
        _sideDish.value = ""
        _price.value = 0.0
        _mainDishPrice.value = "0.0"
        _sideDishPrice.value = "0.0"
        _accompanimentPrice.value = "0.0"
    }
}




