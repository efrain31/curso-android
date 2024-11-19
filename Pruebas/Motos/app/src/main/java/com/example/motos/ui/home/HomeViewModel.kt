package com.example.motos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Inicio para comenzar a ver las motos muevase por el menu"
    }
    val text: LiveData<String> = _text
}