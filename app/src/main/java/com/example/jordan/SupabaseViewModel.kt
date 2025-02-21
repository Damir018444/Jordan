package com.example.jordan

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jordan.SupabaseClient.supabase
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch
import okhttp3.Address
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value
import kotlin.time.Duration.Companion.minutes

//class
class SupabaseViewModel: ViewModel() {

    fun signUp(context: Context, name: String, userEmail: String, userPass: String, callback: (Int) -> Unit) {
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
                            callback(1)
                        }

                        is SessionStatus.LoadingFromStorage -> { Log.e("SIGN_UP", "LoadingFromStorage"); callback(-1) }
                        is SessionStatus.NetworkError -> { Log.e("SIGN_UP", "NetworkError"); callback(-11) }
                        is SessionStatus.NotAuthenticated -> { Log.e("SIGN_UP", "NotAuthenticated"); callback(-111) }
                    }
                }
            } catch (e: Exception) {
                Log.e("SIGN_UP", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }



    fun signIn(context: Context, userEmail: String, userPass: String, callback: (Int) -> Unit) {
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
                            Log.w("SIGN_IN", "Logged Successfully!")
                            callback(1)
                        }

                        is SessionStatus.LoadingFromStorage -> { Log.e("SIGN_IN", "LoadingFromStorage"); callback(-1) }
                        is SessionStatus.NetworkError -> { Log.e("SIGN_IN", "NetworkError"); callback(-11) }
                        is SessionStatus.NotAuthenticated -> { Log.e("SIGN_IN", "NotAuthenticated"); callback(-111) }
                    }
                }
            } catch (e: Exception){
                Log.e("SIGN_IN", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }



    fun getOTP(userEmail: String, callback: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                supabase.auth.signInWith(OTP) {
                    email = userEmail
                    createUser = false
                }

                callback(1)
            } catch (e: Exception){
                Log.e("GET_OTP", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }


    fun checkOTP(context: Context, userEmail: String, code: String, callback: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                supabase.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = userEmail,
                    token = code
                )

                supabase.auth.sessionStatus.collect{ status ->
                    when(status){
                        is SessionStatus.Authenticated -> {
                            saveToken(context)
                            Log.w("SIGN_IN", "Logged Successfully!")
                            callback(1)
                        }

                        is SessionStatus.LoadingFromStorage -> { Log.e("CHECK_OTP", "LoadingFromStorage"); callback(-1) }
                        is SessionStatus.NetworkError -> { Log.e("CHECK_OTP", "NetworkError"); callback(-11) }
                        is SessionStatus.NotAuthenticated -> { Log.e("CHECK_OTP", "NotAuthenticated"); callback(-111) }
                    }
                }
            } catch (e: Exception){
                Log.e("CHECK_OTP", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }







    fun signOut(callback: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                supabase.auth.signOut()
                supabase.auth.sessionStatus.collect{ status ->
                    when(status){
                        is SessionStatus.Authenticated -> { Log.e("CHECK_OTP", "Authenticated"); callback(-111) }
                        is SessionStatus.NetworkError -> { Log.e("CHECK_OTP", "NetworkError"); callback(-11) }
                        is SessionStatus.LoadingFromStorage -> { Log.e("CHECK_OTP", "LoadingFromStorage"); callback(-1) }

                        is SessionStatus.NotAuthenticated -> {
                            Log.w("CHECK_OTP", "NotAuthenticated");
                            callback(1)
                        }
                    }
                }
            } catch (e: Exception){
                Log.e("SIGN_OUT", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
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













    //DATABASE
    private fun insertData(authId: String, name: String){
        viewModelScope.launch {
            try {
                val data = mapOf("auth_id" to authId, "name" to name)
                supabase.postgrest.from("jordan").insert(data)
                Log.e("INSERT_DATA", "Data Inserted Successfully!")
            } catch (e: Exception) {
                Log.e("INSERT_DATA", "Error has appeared: ${e.message}")
            }
        }
    }


    fun getUserDataType1(context: Context, onDataRetrieved: (list: List<String>) -> Unit){
        viewModelScope.launch {
            var list: List<String> = listOf()
            val authId = getAuthId(context)
            if(authId != null) {
                try {
                    val result = supabase.from("jordan")
                        .select(Columns.list("name")){
                            filter {
                                eq("auth_id", authId)
                            }
                        }.decodeSingle<Map<String, String>>()

                    val userEmail = supabase.auth.currentUserOrNull()?.email
                    if (userEmail == null) Log.e("GET_ALL_USER_DATA", "User Email Is NULL")

                    list = list.plus(arrayOf(result["name"] as String, userEmail?:"???"))

                    onDataRetrieved(list)
                } catch (e: Exception) {
                    Log.e("GET_ALL_USER_DATA", "Error has appeared: ${e.message}")
                }
            } else Log.e("GET_ALL_USER_DATA", "authId Is NULL")
        }
    }

    fun getUserDataType2(context: Context, onDataRetrieved: (list: List<String>) -> Unit){
        viewModelScope.launch {
            var list: List<String> = listOf()
            val authId = getAuthId(context)
            if(authId != null) {
                try {
                    val result = supabase.from("jordan")
                        .select(Columns.list("name", "surname", "address")){
                            filter {
                                eq("auth_id", authId)
                            }
                        }.decodeSingle<Map<String, String>>()

                    val userPhone = supabase.auth.currentUserOrNull()?.phone
                    if (userPhone == null) Log.e("GET_ALL_USER_DATA", "User Email Is NULL")

                    list = list.plus(arrayOf(result["name"] as String, result["surname"] as String,
                        result["address"] as String, userPhone?:"???"))

                    onDataRetrieved(list)
                } catch (e: Exception) {
                    Log.e("GET_ALL_USER_DATA", "Error has appeared: ${e.message}")
                }
            } else Log.e("GET_ALL_USER_DATA", "authId Is NULL")
        }
    }


    fun updateDataType1(context: Context, name: String, newEmail: String, newPass: String, callback: (Int) -> Unit){
        viewModelScope.launch {
            val authId = getAuthId(context)
            if(authId != null) {
                try {
                    supabase.postgrest.from("jordan").update({ set("name", name) }) {
                        filter {
                            eq("auth_id", authId)
                        }
                    }

                    supabase.auth.updateUser {
                        email = newEmail
                        password = newPass
                    }

                    callback(1)
                } catch (e: Exception) {
                    callback(-1)
                    Log.e("UPDATE_DATA", "Error has appeared: ${e.message}")
                }
            } else {
                callback(-11)
                Log.e("GET_ALL_USER_DATA", "authId Is NULL")
            }
        }
    }

    fun updateDataType2(context: Context, name: String, surname: String, userPhone: String, address: String, callback: (Int) -> Unit){
        viewModelScope.launch {
            val authId = getAuthId(context)
            if(authId != null) {
                try {
                    supabase.postgrest.from("jordan").update({
                        set("name", name)
                        set("surname", surname)
                        set("address", address)
                    }) {
                        filter {
                            eq("auth_id", authId)
                        }
                    }

                    /*supabase.auth.updateUser {
                        phone = userPhone
                    }*/

                    callback(1)
                } catch (e: Exception) {
                    callback(-1)
                    Log.e("UPDATE_DATA", "Error has appeared: ${e.message}")
                }
            } else {
                callback(-11)
                Log.e("GET_ALL_USER_DATA", "authId Is NULL")
            }
        }
    }














    //STORAGE
    fun createBucket(name: String, callback: (Int) -> Unit){
        viewModelScope.launch {
            try {
                supabase.storage.createBucket(id = name){
                    public = false
                    fileSizeLimit = 10.megabytes
                }
                callback(1)
            } catch (e: Exception){
                Log.e("CREATE_BUCKET", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }


    fun uploadFile(context: Context, bucketName: String, byteArray: ByteArray, callback: (Int) -> Unit){
        viewModelScope.launch {
            try {
                val bucket = supabase.storage[bucketName]
                val authId = getAuthId(context)
                if (authId != null) {
                    bucket.upload("$authId.png", byteArray, true)
                    callback(1)
                } else Log.e("UPLOAD_FILE", "AuthId is NULL")
            } catch (e: Exception){
                Log.e("UPLOAD_FILE", "Error has appeared: ${e.message}")
                callback(-1)
            }
        }
    }


    fun getFile(context: Context, bucketName: String, onImageUrlRetrieved:(url: String) -> Unit){
        viewModelScope.launch {
            try {
                val authId = getAuthId(context)
                if (authId != null) {
                    val bucket = supabase.storage.from(bucketName)
                    val url = bucket.publicUrl("$authId.png")
                    onImageUrlRetrieved(url)
                } else Log.e("GET_FILE", "AuthId is NULL")
            } catch (e: Exception){
                Log.e("GET_FILE", "Error has appeared: ${e.message}")
            }
        }
    }


    /*fun getFile(context: Context, bucketName: String, onImageUrlRetrieved:(url: String) -> Unit){
        viewModelScope.launch {
            try {
                val authId = getAuthId(context)
                if (authId != null) {
                    val bucket = supabase.storage[bucketName]
                    val url = bucket.createSignedUploadUrl("$authId.png")
                    onImageUrlRetrieved(url.toString())
                } else Log.e("GET_FILE", "AuthId is NULL")
            } catch (e: Exception){
                Log.e("GET_FILE", "Error has appeared: ${e.message}")
            }
        }
    }*/
















    //TOKEN
    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = supabase.auth.currentAccessTokenOrNull() ?: ""
            val authId = supabase.auth.currentUserOrNull()?.id ?: ""
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accessToken)
            sharedPref.saveStringData("authId", authId)
            Log.e("USER_ID", authId)
            Log.e("ACCESS_TOKEN", accessToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

    private fun getAuthId(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("authId")
    }
}