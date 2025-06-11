package com.example.yamb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel:ViewModel() {
    var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()

    var email = mutableStateOf("")
    var password1 = mutableStateOf("")
    var password2 = mutableStateOf("")
    var name = mutableStateOf("")

    val generalError = mutableStateOf<String?>(null)

    fun register(navController: NavController) {
        generalError.value = null

        val enteredName = name.value.trim()
        val enteredEmail = email.value.trim()
        val enteredPassword1 = password1.value
        val enteredPassword2 = password2.value

        if (enteredEmail.isBlank() ||
            enteredPassword1.isBlank() ||
            enteredPassword2.isBlank() ||
            enteredName.isBlank()
        ) {
            generalError.value = "Molimo popunite sva polja"
            return
        }

        if (enteredPassword1 != enteredPassword2) {
            generalError.value = "Lozinke se ne podudaraju"
            return
        }

        db.collection("Users")
            .whereEqualTo("name", enteredName)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    generalError.value = "Ime je već zauzeto"
                } else {
                    auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword1)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val uid = auth.currentUser?.uid ?: run {
                                    generalError.value = "Neuspjelo dohvaćanje korisničkog ID-a"
                                    return@addOnCompleteListener
                                }

                                val user = mapOf(
                                    "name" to enteredName,
                                    "email" to enteredEmail,
                                    "password" to enteredPassword1
                                )

                                db.collection("Users").document(uid).set(user)
                                    .addOnSuccessListener {
                                        navController.navigate("login") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    }
                                    .addOnFailureListener {
                                        generalError.value = "Greška pri spremanju korisnika u bazu"
                                    }   
                            } else {
                                val exception = task.exception
                                when (exception) {
                                    is FirebaseAuthUserCollisionException -> {
                                        generalError.value = "Email je već zauzet"
                                    }
                                    else -> {
                                        generalError.value = exception?.localizedMessage ?: "Greška pri registraciji"
                                    }
                                }
                            }
                        }
                }
            }
            .addOnFailureListener { e ->
                generalError.value = "Greška prilikom provjere imena: ${e.localizedMessage}"
            }
    }
}
