package com.example.yamb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yamb.viewModels.HomeViewModel

@Composable
fun OrangeButtonPadding(onClick: () -> Unit,text:String) {
    Button(modifier = Modifier
        .padding(15.dp)
        .width(168.dp)
        .height(56.dp),onClick=onClick, colors = ButtonDefaults.buttonColors( containerColor = Color(0xffDE541E))) {
        Text(text)
    }
}

@Composable
fun Home(navController: NavController,viewModel:HomeViewModel = viewModel()) {
    Box(modifier=Modifier.fillMaxSize().background(color = Color(0xffECE4B7)))
    {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("Dobrodošao, ${viewModel.userData.value?.username}", color = Color(0xff3e963e), fontSize = 40.sp)
            OrangeButtonPadding({ navController.navigate("game") },"Započni igru")
            OrangeButtonPadding({viewModel.logout(navController)},"Odjava")

        }
    }

}
