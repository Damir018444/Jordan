package com.example.jordan

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jordan.SupabaseClient.client
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class SupabaseAuthViewModel: ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(context: Context, userEmail: String, userPass: String) {
        viewModelScope.launch {
            try {
                client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPass
                }
                saveToken(context)
                _userState.value = UserState.Success("Registered Successfully!")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error has appeared (signUp): ${e.message}")
            }
        }
    }



    fun signIn(context: Context, userEmail: String, userPass: String) {
        viewModelScope.launch {
            try {
                client.auth.signInWith(Email) {
                    email = userEmail
                    password = userPass
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged Successfully!")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error has appeared (signIn): ${e.message}")
            }
        }
    }



    fun signOut(){
        viewModelScope.launch {
            try {
                client.auth.signOut()
                _userState.value = UserState.Success("Signed Out Successfully!")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error has appeared (signOut): ${e.message}")
            }
        }
    }



    fun isUserSignedIn(context: Context){
        viewModelScope.launch {
            try {
                val token = getToken(context)

                if(token.isNullOrEmpty()){
                    _userState.value = UserState.Error("User is signed out!")
                } else {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User is signed in!")
                }

                _userState.value = UserState.Success("Signed Out Successfully!")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error has appeared (isUserLoggedIn): ${e.message}")
            }
        }
    }












    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accesToken = client.auth.currentAccessTokenOrNull() ?: ""
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accesToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }
}