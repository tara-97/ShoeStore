package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

private const val TAG = "ShoeViewModel"
class ShoeViewModel : ViewModel() {
    private var _shoeList:MutableLiveData<MutableList<Shoe>> = MutableLiveData()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _isLogged:MutableLiveData<Boolean> = MutableLiveData()
    val isLogged:LiveData<Boolean>
    get() = _isLogged


    init {
        _shoeList.value = mutableListOf()
        _isLogged.value = false
    }

    fun addShoe(shoe:Shoe){
        _shoeList.value?.add(shoe)

        Log.d(TAG, "addShoe: Total shoes is ${shoeList.value?.size}")
    }
    fun changeLoginState(){
        _isLogged.value = !_isLogged.value!!
    }
}