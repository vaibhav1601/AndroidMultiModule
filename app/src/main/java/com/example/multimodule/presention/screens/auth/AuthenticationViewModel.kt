package com.example.multimodule.presention.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodule.util.Constant
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {
    var authantication= mutableStateOf(false)
    var loadingState = mutableStateOf(false)
        private set


    fun setLoading(isLoading: Boolean) {
        loadingState.value = isLoading
    }


    fun signInWithMangoAtlas(
        tokenId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        viewModelScope.launch {
            try {

                val result = withContext(Dispatchers.IO) {
                    App.create(Constant.APP_ID).login(
                       // Credentials.google(tokenId, GoogleAuthType.ID_TOKEN)
                        Credentials.jwt(tokenId)
                    )

                }.loggedIn

                withContext(Dispatchers.Main){
                    if(result){
                        onSuccess()
                        delay(600)
                        authantication.value=true
                    }else{
                        onError(Exception("User Not login "))
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main){
                    onError(ex)
                }
            }
        }

    }
}