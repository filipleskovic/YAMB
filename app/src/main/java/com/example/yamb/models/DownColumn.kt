package com.example.yamb.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yamb.interfaces.Column

class DownColumn:Column{
    var currentIndex by mutableStateOf(0)

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
    override  var scale by mutableStateOf("")
    override var fullHouse by mutableStateOf("")
    override var poker by mutableStateOf("")
    override var yamb by mutableStateOf("")
    override var sum3 by mutableStateOf("")
    override var lineSum by mutableStateOf("")

    override val states = listOf(
        mutableStateOf(true),   // isOneEnabled
        mutableStateOf(false),  // isTwoEnabled
        mutableStateOf(false),  // isThreeEnabled
        mutableStateOf(false),  // isFourEnabled
        mutableStateOf(false),  // isFiveEnabled
        mutableStateOf(false),  // isSixEnabled
        mutableStateOf(false),  // isSum1Enabled
        mutableStateOf(false),  // isMaxEnabled
        mutableStateOf(false),  // isMinEnabled
        mutableStateOf(false),  // isSum2Enabled
        mutableStateOf(false),  // is2PairEnabled
        mutableStateOf(false),  // isScaleEnabled
        mutableStateOf(false),  // isFHEnabled
        mutableStateOf(false),  // isPokerEnabled
        mutableStateOf(false),  // isYambEnabled
        mutableStateOf(false),  // isSum3Enabled
        mutableStateOf(false)   // isLineSumEnabled
    )
    private fun calculateSum1(){
        var sum=(one.toInt()+two.toInt()+three.toInt()+four.toInt()+five.toInt()+six.toInt())
        if(sum>=60)
            sum1=(sum+30).toString()
        else
            sum1=sum.toString()
    }
    private fun calculateSum2(){
        var sum = max.toInt()-min.toInt()
        if(sum<=0)
        {
            sum2="0";
        }
        else{
            sum2=(one.toInt()*sum).toString()  ;
        }
    }
    private fun calculateSum3(){
        sum3 = (yamb.toInt()+poker.toInt()+fullHouse.toInt()+twoPair.toInt()+scale.toInt()).toString()
    }
    private fun calculateLineSum(){
        lineSum=(sum3.toInt()+sum2.toInt()+sum1.toInt()).toString();
    }
    override fun changeField(){
        states[currentIndex].value=false;
        if(currentIndex==14) {
            calculateSum3()
            calculateLineSum()
            return;
        }
        if(currentIndex==5)
        {
            calculateSum1()
            currentIndex+=2
            states[currentIndex].value=true
        }
        else if (currentIndex==8){
            calculateSum2()
            currentIndex+=2
            states[currentIndex].value=true

        }
        else {
            currentIndex+=1
            states[currentIndex].value=true
        }
    }
}