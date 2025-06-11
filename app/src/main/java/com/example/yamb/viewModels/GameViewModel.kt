package com.example.yamb.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yamb.interfaces.Column
import com.example.yamb.models.CallColumn
import com.example.yamb.models.DownColumn
import com.example.yamb.models.FreeColumn
import com.example.yamb.models.SignColumn
import com.example.yamb.models.UpColumn
import kotlin.random.Random

class GameViewModel:ViewModel() {

    var numberOfRolls = mutableStateOf(0)
    var isCall = mutableStateOf(false)
    var dicesValue = mutableStateOf(List(5) { 0 })
        private set
    var dicesToRoll = mutableStateOf(List(5) { true })
        private set
    val columns = mutableMapOf<String, Column>(
        "down" to DownColumn(),
        "up" to UpColumn(),
        "free" to FreeColumn(),
        "call" to CallColumn(),
        "sign" to SignColumn(),
    )
    var totalPoints = mutableStateOf("")
    var activeColumn = mutableStateOf(columns["down"]!!)
        private set
    val calls = mutableStateListOf(*Array(17) { false })
    fun isCallAvailable():Boolean{
        return numberOfRolls.value<2
    }
    fun rollDices() {
        numberOfRolls.value++
        val newDices = dicesValue.value.mapIndexed { index, oldValue ->
            if (dicesToRoll.value[index]) Random.nextInt(1, 7) else oldValue
        }
        dicesValue.value = newDices
    }
    fun changeDiceState(index:Int){
        val current = dicesToRoll.value.toMutableList()
        current[index] = !current[index]
        dicesToRoll.value = current
    }
    private fun setActiveColumn(name: String) {
        columns[name]?.let {
            activeColumn.value = it
        }
    }
    fun updateOne(column: String){
        if(column=="call" && isCall.value==false)
        {
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            isCall.value=false;
            val newVal = dicesValue.value.count { it == 1 }
            activeColumn.value.one = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            numberOfRolls.value=0
            calculateTotalPoints()
        }

    }
    fun updateTwo(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            isCall.value=false;
            val newVal = dicesValue.value.count { it == 2 }*2
            activeColumn.value.two = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            numberOfRolls.value=0
            calculateTotalPoints()

        }
    }
    fun updateThree(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.count { it == 3 }*3
            activeColumn.value.three = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateFour(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.count { it == 4 }*4
            activeColumn.value.four = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateFive(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;

        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.count { it == 5 }*5
            activeColumn.value.five = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateSix(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.count { it == 6 }*6
            activeColumn.value.six = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateMax(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.sum()
            activeColumn.value.max = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateMin(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = dicesValue.value.sum()
            activeColumn.value.min = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    private fun calculateTwoPairs(): Int {
        var result = 0
        val counts = dicesValue.value.groupingBy { it }.eachCount()
        val pairs = counts.values.count { it >= 2 }
        if(pairs==2){
            val sumOfPairs = counts
                .filter { it.value >= 2 }
                .keys
                .take(2)
                .sumOf { it * 2 }
            result=sumOfPairs+10
        }
        return result
    }
    private fun calculateScale():Int{
        var result =0
        if( dicesValue.value.toSet() == setOf(1, 2, 3, 4, 5)) result = 35
        else if(dicesValue.value.toSet() == setOf(6, 2, 3, 4, 5)) result = 45
        return result
    }
    private fun calculateFullHouse():Int{
        var result = 0
        val counts = dicesValue.value.groupingBy { it }.eachCount().values.sorted()
        if( counts == listOf(2, 3))
        {
            result=dicesValue.value.sum()+30;
        }
        return result
    }
    private fun calculatePoker():Int{
        val counts = dicesValue.value.groupingBy { it }.eachCount()
        val pokerValue = counts.entries.find { it.value >= 4 }?.key
        return if (pokerValue != null) pokerValue * 4+40 else 0
    }
    private fun calculateYamb():Int{
        val counts = dicesValue.value.groupingBy { it }.eachCount()
        val yamb = counts.entries.find { it.value == 5 }?.key
        return if (yamb != null) yamb * 5+50 else 0
    }
    fun updateTwoPair(column: String){
        if(column=="call" && isCall.value==false) {
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value = true;
        }
        else{
            setActiveColumn(column)
            val newVal = calculateTwoPairs()
            activeColumn.value.twoPair = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateScale(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = calculateScale()
            activeColumn.value.scale = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateFullHouse(column: String){
        if(column=="call" && isCall.value==false) {
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value = true;
        }
        else{
            setActiveColumn(column)
            val newVal = calculateFullHouse()
            activeColumn.value.fullHouse = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updatePoker(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else{
            setActiveColumn(column)
            val newVal = calculatePoker()
            activeColumn.value.poker = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value=(List(5) { true })
            dicesValue.value=(List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun updateYamb(column: String){
        if(column=="call" && isCall.value==false){
            setActiveColumn(column)
            activeColumn.value.changeField()
            isCall.value=true;
        }
        else {
            setActiveColumn(column)
            val newVal = calculateYamb()
            activeColumn.value.yamb = newVal.toString()
            activeColumn.value.changeField()
            dicesToRoll.value = (List(5) { true })
            dicesValue.value = (List(5) { 0 })
            isCall.value=false;
            numberOfRolls.value=0
            calculateTotalPoints()
        }
    }
    fun calculateTotalPoints(){
        if(columns["down"]?.lineSum!!.isNotEmpty() && columns["up"]?.lineSum!!.isNotEmpty() && columns["free"]?.lineSum!!.isNotEmpty() &&columns["call"]?.lineSum!!.isNotEmpty() )
        {
            totalPoints.value=(columns["down"]?.lineSum!!.toInt()+columns["up"]?.lineSum!!.toInt()+columns["free"]?.lineSum!!.toInt()+columns["call"]?.lineSum!!.toInt()).toString()
        }
    }
}