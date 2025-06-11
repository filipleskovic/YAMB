package com.example.yamb.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yamb.interfaces.Column

class FreeColumn : Column {
    override val states = listOf(
        mutableStateOf(true),   // isOneEnabled
        mutableStateOf(true),  // isTwoEnabled
        mutableStateOf(true),  // isThreeEnabled
        mutableStateOf(true),  // isFourEnabled
        mutableStateOf(true),  // isFiveEnabled
        mutableStateOf(true),  // isSixEnabled
        mutableStateOf(false),  // isSum1Enabled
        mutableStateOf(true),  // isMaxEnabled
        mutableStateOf(true),  // isMinEnabled
        mutableStateOf(false),  // isSum2Enabled
        mutableStateOf(true),  // is2PairEnabled
        mutableStateOf(true),  // isScaleEnabled
        mutableStateOf(true),  // isFHEnabled
        mutableStateOf(true),  // isPokerEnabled
        mutableStateOf(true),  //
        mutableStateOf(false),  // isSum3Enabled
        mutableStateOf(false)   // isLineSumEnabled
    )
    override var one by mutableStateOf("")
    override var two by mutableStateOf("")
    override var three by mutableStateOf("")
    override var four by mutableStateOf("")
    override var five by mutableStateOf("")
    override var six by mutableStateOf("")
    override var sum1 by mutableStateOf("")
    override var max by mutableStateOf("")
    override var min by mutableStateOf("")
    override var sum2 by mutableStateOf("")
    override var twoPair by mutableStateOf("")
    override var scale by mutableStateOf("")
    override var fullHouse by mutableStateOf("")
    override var poker by mutableStateOf("")
    override var yamb by mutableStateOf("")
    override var sum3 by mutableStateOf("")
    override var lineSum by mutableStateOf("")

    private fun calculateSum1() {
        var sum =
            (one.toInt() + two.toInt() + three.toInt() + four.toInt() + five.toInt() + six.toInt())
        if (sum >= 60)
            sum1 = (sum + 30).toString()
        else
            sum1 = sum.toString()
    }

    private fun calculateSum2() {
        var sum = max.toInt() - min.toInt()
        if (sum <= 0) {
            sum2 = "0";
        } else {
            sum2 = (one.toInt() * sum).toString();
        }
    }

    private fun calculateSum3() {
        sum3 =
            (yamb.toInt() + poker.toInt() + fullHouse.toInt() + twoPair.toInt() + scale.toInt()).toString()
    }

    private fun calculateLineSum() {
        lineSum = (sum3.toInt() + sum2.toInt() + sum1.toInt()).toString();
    }
    private fun disableFields() {
        if (one.isNotEmpty()) states[0].value = false
        if (two.isNotEmpty()) states[1].value = false
        if (three.isNotEmpty()) states[2].value = false
        if (four.isNotEmpty()) states[3].value = false
        if (five.isNotEmpty()) states[4].value = false
        if (six.isNotEmpty()) states[5].value = false
        if (max.isNotEmpty()) states[7].value = false
        if (min.isNotEmpty()) states[8].value = false
        if (twoPair.isNotEmpty()) states[10].value = false
        if (scale.isNotEmpty()) states[11].value = false
        if (fullHouse.isNotEmpty()) states[12].value = false
        if (poker.isNotEmpty()) states[13].value = false
        if (yamb.isNotEmpty()) states[14].value = false
    }
    override fun changeField() {
        if(sum1.isEmpty() && one.isNotEmpty() && two.isNotEmpty() && three.isNotEmpty() && four.isNotEmpty() && five.isNotEmpty() && six.isNotEmpty())
            calculateSum1()
        if(sum2.isEmpty() && one.isNotEmpty() && max.isNotEmpty() && min.isNotEmpty())
            calculateSum2()
        if(sum3.isEmpty() && twoPair.isNotEmpty() && scale.isNotEmpty() && fullHouse.isNotEmpty() && poker.isNotEmpty() && yamb.isNotEmpty())
            calculateSum3()
        if(lineSum.isEmpty() && sum3.isNotEmpty() && sum2.isNotEmpty() && sum3.isNotEmpty())
            calculateLineSum()
        disableFields()
    }
}
