package com.example.yamb.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yamb.interfaces.Column

class SignColumn:Column {
    override var one by mutableStateOf("1")
    override var two by mutableStateOf("2")
    override var three by mutableStateOf("3")
    override var four by mutableStateOf("4")
    override var five by mutableStateOf("5")
    override var six by mutableStateOf("6")
    override var sum1 by mutableStateOf("S1")
    override var max by mutableStateOf("MA")
    override var min by mutableStateOf("MI")
    override var sum2 by mutableStateOf("S2")
    override var twoPair by mutableStateOf("2P")
    override var scale by mutableStateOf("S")
    override var fullHouse by mutableStateOf("FH")
    override var poker by mutableStateOf("P")
    override var yamb by mutableStateOf("Y")
    override var sum3 by mutableStateOf("S3")
    override var lineSum by mutableStateOf("LS")
    override val states: List<MutableState<Boolean>> = listOf()

    override fun changeField(){
        return
    }
}