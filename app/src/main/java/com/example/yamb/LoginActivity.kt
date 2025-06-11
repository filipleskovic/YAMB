package com.example.yamb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yamb.viewModels.LoginViewModel

@Composable
fun InputField (text:String,onValueChange: (String) -> Unit,isPassword:Boolean) {
    TextField(text,colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xff3e963e),
            unfocusedContainerColor     = Color(0xff3e963e)),
        onValueChange = onValueChange,
        visualTransformation = if(isPassword)  PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
        .width(250.dp)
        .height(80.dp)
        .padding(0.dp, 7.dp)
        .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
        .border(
            2.dp, color = Color(0xff000000),
            shape = RoundedCornerShape(8.dp)
        ),
        singleLine = true
    )
}

@Composable
fun OrangeButton(onClick: () -> Unit,text:String) {
    Button(modifier = Modifier
        .width(128.dp)
        .height(56.dp),onClick=onClick, colors = ButtonDefaults.buttonColors( containerColor = Color(0xffDE541E))) {
        Text(text)
    }
}

@Composable
fun RegisterNav(navController: NavController) {
    Text(text = "Nemaš račun? Registriraj se !",
        color =Color(0xffDE541E),
        fontSize = 12.sp,
        modifier = Modifier.clickable {navController.navigate("register")  })
}
@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel = viewModel())
{
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffECE4B7))){
        Column (modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            InputField(viewModel.email.value, onValueChange = { viewModel.email.value = it },false)
            InputField(viewModel.password.value, onValueChange = { viewModel.password.value = it },true)
            OrangeButton(onClick = {viewModel.login(navController)},"Prijava")
            RegisterNav(navController)
            viewModel.generalError.value?.let { error ->
                Text(text = error, color = Color.Red, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
