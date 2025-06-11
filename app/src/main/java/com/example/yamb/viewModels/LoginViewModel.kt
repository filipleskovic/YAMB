package com.example.yamb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    val auth = FirebaseAuth.getInstance()
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    val generalError = mutableStateOf<String?>(null)

    fun login(navController: NavController){
        if (email.value.isBlank() ||
            password.value.isBlank()
        ) {
            generalError.value = "Molimo popunite sva polja"
            return
        }
        else
        {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate("home")
                    } else {
                        generalError.value="Netočan email ili lozinka"
                    }
                }
        }

    }
}
