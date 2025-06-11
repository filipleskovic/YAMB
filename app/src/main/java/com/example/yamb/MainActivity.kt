package com.example.yamb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yamb.ui.theme.YambTheme
import com.example.yamb.viewModels.GameViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yamb.models.SignColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            YambTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.background(color = Color.White).padding(innerPadding)) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box(modifier = Modifier.fillMaxWidth())
                            {
                                NavHost(navController = navController, startDestination = "game",)
                                {
                                    composable("game") {
                                        Game(navController = navController)
                                    }
                                    composable("login") {
                                        LoginForm(navController = navController)
                                    }
                                    composable("register"){
                                        RegisterForm(navController = navController)
                                    }
                                    composable("home")
                                    {
                                        Home(navController=navController)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Field(text:String,modifier: Modifier = Modifier,isEnabled:Boolean,type: String,onClick: (() -> Unit)){
    val backGroundColor = if(type=="sum")Color(0x4d43ff64)else if(text.isNotEmpty()) Color(0xBBDE541E) else if(isEnabled) Color.White else Color.LightGray
    Box(
        modifier = Modifier
            .size(34.dp)
            .background(backGroundColor, shape = RoundedCornerShape(8.dp))
            .border(2.dp, Color(0xffDE541E), RoundedCornerShape(8.dp)  )
            .then(
                if (isEnabled) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onClick()
                    }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}
@Composable
fun GameColumn(viewModel: GameViewModel = viewModel(),tag:String ){
    var column = remember {  viewModel.columns[tag]!!}
    Box{
        Column {
            Field(column.one,modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[0].value,"normal", onClick = {viewModel.updateOne(tag)})
            Field(column.two,modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[1].value,"normal",onClick = {viewModel.updateTwo(tag)})
            Field(column.three, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[2].value,"normal",onClick = { viewModel.updateThree(tag)  })
            Field(column.four, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[3].value,"normal",onClick = { viewModel.updateFour(tag) })
            Field(column.five, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[4].value,"normal",onClick = { viewModel.updateFive(tag) })
            Field(column.six, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[5].value,"normal",onClick = {viewModel.updateSix(tag)})
            Field(column.sum1, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[6].value,"sum", onClick = {})
            Field(column.max, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[7].value,"normal",onClick = {viewModel.updateMax(tag)})
            Field(column.min, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[8].value,"normal",onClick = {viewModel.updateMin(tag)})
            Field(column.sum2, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[9].value,"sum",onClick = {})
            Field(column.twoPair, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[10].value,"normal",onClick = {viewModel.updateTwoPair(tag)})
            Field(column.scale, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[11].value,"normal",onClick = {viewModel.updateScale(tag)})
            Field(column.fullHouse, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[12].value,"normal",onClick = {viewModel.updateFullHouse(tag)})
            Field(column.poker, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[13].value,"normal",onClick = {viewModel.updatePoker(tag)})
            Field(column.yamb, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[14].value,"normal",onClick = {viewModel.updateYamb(tag)})
            Field(column.sum3, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[15].value,"sum",onClick = {})
            Field(column.lineSum, modifier = Modifier.padding(4.dp),!viewModel.isCall.value && column.states[16].value,"sum",onClick = {})
        }
    }
}
@Composable
fun CallColumn(viewModel: GameViewModel = viewModel(),tag:String ){
    var column = remember {  viewModel.columns[tag]!!}
    Box{
        Column {
            Field(column.one,modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[0]) && (viewModel.calls[0] || (!viewModel.isCall.value && column.states[0].value)),"normal", onClick = {viewModel.updateOne(tag);viewModel.calls[0]=!viewModel.calls[0]})
            Field(column.two,modifier = Modifier.padding(4.dp), (viewModel.isCallAvailable() || viewModel.calls[1]) && (viewModel.calls[1] || (!viewModel.isCall.value && column.states[1].value)),"normal",onClick = {viewModel.updateTwo(tag); viewModel.calls[1]=!viewModel.calls[1]})
            Field(column.three, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[2]) && (viewModel.calls[2] || (!viewModel.isCall.value && column.states[2].value)),"normal",onClick = { viewModel.updateThree(tag);viewModel.calls[2]=!viewModel.calls[2]  })
            Field(column.four, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[3]) && (viewModel.calls[3] || (!viewModel.isCall.value && column.states[3].value)),"normal",onClick = { viewModel.updateFour(tag);viewModel.calls[3]=!viewModel.calls[3] })
            Field(column.five, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[4]) && (viewModel.calls[4] || (!viewModel.isCall.value && column.states[4].value)),"normal",onClick = { viewModel.updateFive(tag);viewModel.calls[4]=!viewModel.calls[4] })
            Field(column.six, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[5]) && (viewModel.calls[5] || (!viewModel.isCall.value && column.states[5].value)),"normal",onClick = {viewModel.updateSix(tag);viewModel.calls[5]=!viewModel.calls[5]})
            Field(column.sum1, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[6]) && (viewModel.calls[6] || (!viewModel.isCall.value && column.states[6].value)),"sum", onClick = {})
            Field(column.max, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[7]) && (viewModel.calls[7] || (!viewModel.isCall.value && column.states[7].value)),"normal",onClick = {viewModel.updateMax(tag);viewModel.calls[7]=!viewModel.calls[7]})
            Field(column.min, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[8]) && (viewModel.calls[8] || (!viewModel.isCall.value && column.states[8].value)),"normal",onClick = {viewModel.updateMin(tag);viewModel.calls[8]=!viewModel.calls[8]})
            Field(column.sum2, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[9]) && (viewModel.calls[9] || (!viewModel.isCall.value && column.states[9].value)),"sum",onClick = {})
            Field(column.twoPair, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[10]) && (viewModel.calls[10] || (!viewModel.isCall.value && column.states[10].value)),"normal",onClick = {viewModel.updateTwoPair(tag);viewModel.calls[10]=!viewModel.calls[10]})
            Field(column.scale, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[11]) && (viewModel.calls[11] || (!viewModel.isCall.value && column.states[11].value)),"normal",onClick = {viewModel.updateScale(tag);viewModel.calls[11]=!viewModel.calls[11]})
            Field(column.fullHouse, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[12]) && (viewModel.calls[12] || (!viewModel.isCall.value && column.states[12].value)),"normal",onClick = {viewModel.updateFullHouse(tag);viewModel.calls[12]=!viewModel.calls[12]})
            Field(column.poker, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[13]) && (viewModel.calls[13] || (!viewModel.isCall.value && column.states[13].value)),"normal",onClick = {viewModel.updatePoker(tag);viewModel.calls[13]=!viewModel.calls[13]})
            Field(column.yamb, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[14]) && (viewModel.calls[14] || (!viewModel.isCall.value && column.states[14].value)),"normal",onClick = {viewModel.updateYamb(tag);viewModel.calls[14]=!viewModel.calls[14]})
            Field(column.sum3, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[15]) && (viewModel.calls[15] || (!viewModel.isCall.value && column.states[15].value)),"sum",onClick = {})
            Field(column.lineSum, modifier = Modifier.padding(4.dp),(viewModel.isCallAvailable() || viewModel.calls[16]) && (viewModel.calls[16] || (!viewModel.isCall.value && column.states[16].value)),"sum",onClick = {})
        }
    }
}
@Composable
fun SignColumn(viewModel: GameViewModel = viewModel(),tag:String ){
    var column = remember {  viewModel.columns[tag]!!}
    Box{
        Column {
            Field(column.one,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.two,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.three,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.four,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.five,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.six,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.sum1,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.max,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.min,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.sum2,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.twoPair,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.scale,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.fullHouse,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.poker,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.yamb,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.sum3,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
            Field(column.lineSum,modifier = Modifier.padding(4.dp), false,"sum", onClick = {})
        }
    }
}
@Composable
fun Dices(viewModel: GameViewModel = viewModel()) {
    val dices = viewModel.dicesValue.value
    val states= viewModel.dicesToRoll.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp))
    {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = dices[0].toString(),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = if (!states[0])  Color(0x4d43ff64) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.changeDiceState(0) }
                    )
                    Text(
                        text = dices[1].toString(),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = if (!states[1])  Color(0x4d43ff64) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.changeDiceState(1) }


                    )
                    Text(
                        text = dices[2].toString(),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = if (!states[2])  Color(0x4d43ff64) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.changeDiceState(2) }

                    )
                }
                Row(modifier = Modifier.padding(2.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = dices[3].toString(),
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = if (!states[3])  Color(0x4d43ff64) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.changeDiceState(3) }

                    )
                    Text(
                        text = dices[4].toString(),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = if (!states[4])  Color(0x4d43ff64) else Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.changeDiceState(4) }
                    )
                }
            }
            Button(
                modifier = Modifier.width(50.dp).height(50.dp).weight(1f),
                onClick = { if(viewModel.numberOfRolls.value<3)viewModel.rollDices() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffDE541E))) {
                Text("Roll", color = Color.Black)
            }
        }
    }
}
@Composable
fun Game(navController: NavController,viewModel: GameViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffECE4B7))){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Row {
                SignColumn(viewModel,"sign")
                GameColumn(viewModel,"down")
                GameColumn(viewModel,"up")
                GameColumn(viewModel,"free")
                CallColumn(viewModel,"call")
            }
            Field(viewModel.totalPoints.value,modifier = Modifier.padding(4.dp),false,"sum",{})
            Dices(viewModel)
        }
    }

}
