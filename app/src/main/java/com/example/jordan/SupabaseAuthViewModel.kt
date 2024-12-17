package com.example.jordan

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jordan.SupabaseClient.supabase
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class SupabaseAuthViewModel: ViewModel() {

    fun signUp(context: Context, name: String, userEmail: String, userPass: String) {
        viewModelScope.launch {
            try {
                supabase.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPass
                }

                supabase.auth.sessionStatus.collect{ status ->
                    when(status){
                        is SessionStatus.Authenticated -> {
                            saveToken(context)
                            insertData(status.session.user?.id ?: "", name)
                            Log.w("SIGN_UP", "Registered Successfully!")
                        }

                        is SessionStatus.LoadingFromStorage -> Log.e("SIGN_UP", "LoadingFromStorage")
                        is SessionStatus.NetworkError -> Log.e("SIGN_UP", "NetworkError")
                        is SessionStatus.NotAuthenticated -> Log.e("SIGN_UP", "NotAuthenticated")
                    }
                }
            } catch (e: Exception){
                Log.e("SIGN_UP", "Error has appeared: ${e.message}")
            }
        }
    }



    fun signIn(context: Context, userEmail: String, userPass: String) {
        viewModelScope.launch {
            try {
                supabase.auth.signInWith(Email) {
                    email = userEmail
                    password = userPass
                }

                supabase.auth.sessionStatus.collect{ status ->
                    when(status){
                        is SessionStatus.Authenticated -> {
                            saveToken(context)
                            Log.w("SIGN_UP", "Logged Successfully!")
                        }

                        is SessionStatus.LoadingFromStorage -> Log.e("SIGN_IN", "LoadingFromStorage")
                        is SessionStatus.NetworkError -> Log.e("SIGN_IN", "NetworkError")
                        is SessionStatus.NotAuthenticated -> Log.e("SIGN_IN", "NotAuthenticated")
                    }
                }



            } catch (e: Exception){
                Log.e("SIGN_IN", "Error has appeared: ${e.message}")
            }
        }
    }



    fun getOTP(userEmail: String): Int {
        var code = 111111
        viewModelScope.launch {
            try {
                supabase.auth.signInWith(OTP) {
                    email = userEmail
                    createUser = false
                }

            } catch (e: Exception){
                Log.e("SIGN_OUT", "Error has appeared: ${e.message}")
            }
        }
        return 0
    }



    fun signOut(): Int {
        var code = 0
        viewModelScope.launch {
            try {
                supabase.auth.signOut()
                supabase.auth.sessionStatus.collect{ status ->
                    when(status){
                        is SessionStatus.Authenticated -> {
                            Log.e("SIGN_IN", "Authenticated")
                            code = -1
                        }
                        is SessionStatus.LoadingFromStorage -> {
                            Log.e("SIGN_IN", "LoadingFromStorage")
                            code = -1
                        }
                        is SessionStatus.NetworkError -> {
                            Log.e("SIGN_IN", "NetworkError")
                            code = -1
                        }
                        is SessionStatus.NotAuthenticated -> {
                            Log.e("SIGN_IN", "NotAuthenticated")
                            code = 1
                        } else -> {
                            code = -1
                        }
                    }
                }
            } catch (e: Exception){
                Log.e("SIGN_OUT", "Error has appeared: ${e.message}")
            }
        }
        return code
    }



    fun isUserSignedIn(context: Context){
        viewModelScope.launch {
            try {
                val token = getToken(context)

                if(token.isNullOrEmpty()){
                    Log.w("IS_USER_SIGNED_IN", "User is signed out!")
                } else {
                    supabase.auth.retrieveUser(token)
                    supabase.auth.refreshCurrentSession()
                    saveToken(context)
                    Log.w("IS_USER_SIGNED_IN", "User is signed in!")
                }
            } catch (e: Exception){
                Log.e("IS_USER_SIGNED_IN", "Error has appeared: ${e.message}")
            }
        }
    }













    private fun insertData(authId: String, name: String){
        viewModelScope.launch {
            try {
                val data = mapOf("auth_id" to authId, "name" to name)
                supabase.postgrest.from("jordan").insert(data)
            } catch (e: Exception) {
                Log.e("INSERT_DATA", "Error has appeared: ${e.message}")
            }
        }
    }

    fun updateData(name: String){
        viewModelScope.launch {
            val authId = supabase.auth.currentUserOrNull()?.id ?: ""
            supabase.postgrest.from("jordan").insert(listOf(authId, name))
        }
    }














    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = supabase.auth.currentAccessTokenOrNull() ?: ""
            val authId = supabase.auth.currentUserOrNull()?.id ?: ""
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accessToken)
            sharedPref.saveStringData("authId", authId)
            Log.e("USER_ID", authId)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }
}