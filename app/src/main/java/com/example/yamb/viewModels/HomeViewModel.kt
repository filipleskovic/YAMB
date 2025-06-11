package com.example.yamb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
data class UserData(
    val email: String = "",
    val username: String = ""
)
class HomeViewModel:ViewModel() {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    var userEmail = auth.currentUser?.email
    var userData = mutableStateOf<UserData?>(null)
        private set
    init {
        db.collection("Users")
            .whereEqualTo("email", userEmail)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val email = document.getString("email") ?: ""
                    val username = document.getString("name") ?: ""
                    userData.value = UserData(email, username)
                    break
                }
            }
        }
    fun logout(navController: NavController){
        auth.signOut()
        navController.navigate("login")
    }
}