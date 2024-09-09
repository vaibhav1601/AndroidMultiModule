package com.example.multimodule.presentation.components.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.multimodule.util.Constant
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapGoogleButton
import com.stevdzasan.onetap.OneTapSignInState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    loadingState: Boolean,
    onButtonClick: () -> Unit,
    messageBarState:MessageBarState,
    onTapSignGoogle: OneTapSignInState
){
   Scaffold(
       content = {
           AuthenticationContent(
               loadingState,onButtonClick)

       }
   )

    OneTapGoogleButton(state =onTapSignGoogle , clientId = Constant.CLIENT_ID , onTokenIdReceived = {
        token->
        Log.d("Auth",token)
        messageBarState.addSuccess("Successfully Authenticate")

    }, onDialogDismissed = {
        message->
        Log.d("Auth",message)
        messageBarState.addError(Exception(message))


    }
    )
}