package com.example.yamb.interfaces

import androidx.compose.runtime.MutableState

interface Column {
    var one: String
    var two: String
    var three: String
    var four: String
    var five: String
    var six: String
    var sum1: String
    var max: String
    var min: String
    var sum2: String
    var twoPair: String
    var scale: String
    var fullHouse: String
    var poker: String
    var yamb: String
    var sum3: String
    var lineSum: String

    val states: List<MutableState<Boolean>>

    fun changeField()
}