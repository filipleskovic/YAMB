package com.example.yamb.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class Dice{
    var number by mutableStateOf(1)
    fun roll(){
        number=Random.nextInt(1,7)
    }
}