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
import androidx.navigation.NavController
import com.example.yamb.viewModels.RegisterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun RegisterForm(navController: NavController,viewModel:RegisterViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffECE4B7))){
        Column (modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            InputField(viewModel.name.value, onValueChange = { viewModel.name.value = it },false)
            InputField(viewModel.email.value, onValueChange = { viewModel.email.value = it },false)
            InputField(viewModel.password1.value, onValueChange = { viewModel.password1.value = it },true)
            InputField(viewModel.password2.value, onValueChange = { viewModel.password2.value = it },true)
            OrangeButton (onClick = {viewModel.register(navController)},"Registracija")
            viewModel.generalError.value?.let { error ->
                Text(text = error, color = Color.Red, modifier = Modifier.padding(8.dp))
            }
        }
    }
}